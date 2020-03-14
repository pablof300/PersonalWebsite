package me.pabloestrada.exercise.client;

import com.google.gson.*;
import com.google.inject.Inject;
import me.pabloestrada.credentials.CredentialsHelper;
import me.pabloestrada.exercise.core.exercise.StravaRun;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public final class StravaClient {
    private static final String STRAVA_API_BASE_URL = "https://www.strava.com/";
    private static final String EXERCISE_API_DEV_HOST = "localhost";

    // TODO:
    // Create lib for all these consts
    private static final String AUTH_GRANT_TYPE = "authorization_code";
    private static final String REFRESH_GRANT_TYPE = "refresh_token";
    private static final String REFRESH_TOKEN = "strava_refresh_token";
    private static final String ACCESS_TOKEN = "strava_access_token";
    private static final String INVALID_KEY = "";

    private final AuthenticationClient authenticationClient;
    private final SummaryActivityClient summaryActivityClient;
    private final ExerciseTrackerClient exerciseTrackerClient;

    private final String clientId;
    private final String clientSecret;
    private final String persianServiceToken;

    private CredentialsHelper credentialsHelper;

    @Inject
    public StravaClient(final OkHttpClient.Builder httpClient, final CredentialsHelper credentialsHelper) {
        this.credentialsHelper = credentialsHelper;

        final Retrofit stravaRetrofitBuilder = getStravaRetrofitBuilder(httpClient);
        summaryActivityClient = stravaRetrofitBuilder.create(SummaryActivityClient.class);
        authenticationClient = stravaRetrofitBuilder.create(AuthenticationClient.class);
        exerciseTrackerClient = getExerciseTrackerRetrofitBuilder(httpClient).create(ExerciseTrackerClient.class);

        clientId = credentialsHelper.getCredential("strava_client_id").orElse(INVALID_KEY);
        clientSecret = credentialsHelper.getCredential("strava_client_secret").orElse(INVALID_KEY);
        persianServiceToken = credentialsHelper.getCredential("persian_service_token").orElse(INVALID_KEY);
    }

    private Retrofit getExerciseTrackerRetrofitBuilder(final OkHttpClient.Builder httpClient) {
        return getRetrofitBuilder(httpClient, getPersianConnectionString(), ScalarsConverterFactory.create());

    }

    private Retrofit getStravaRetrofitBuilder(final OkHttpClient.Builder httpClient) {
        final Gson gsonConverter = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> {
                            return LocalDateTime.parse(jsonElement.toString().replaceAll("\"", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
                        })
                .create();

        return getRetrofitBuilder(httpClient, STRAVA_API_BASE_URL, GsonConverterFactory.create(gsonConverter));
    }

    private Retrofit getRetrofitBuilder(final OkHttpClient.Builder httpClient, final String baseUrl, final Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .client(httpClient.build())
                .build();
    }

    private String getPersianConnectionString() {
        final Optional<String> rawConnectionString = Optional.ofNullable(System.getProperty("persianRestHost"));
        System.out.println("\n\n\n\n Connecting TO ### " + "http://" + rawConnectionString.orElse(EXERCISE_API_DEV_HOST) + ":8080/" + "\n\n\n\n");
        return "http://" + rawConnectionString.orElse(EXERCISE_API_DEV_HOST) + ":8080/";
    }

    private Optional<String> getAccessToken() {
        final Optional<String> possibleAccessToken = credentialsHelper.getCredential(ACCESS_TOKEN);
        if (possibleAccessToken.isEmpty()) {
            return updateAccessTokenFromCode();
        }
        return possibleAccessToken;
    }

    // TODO:
    // Make this method more redable and cleaner
    private Optional<String> updateAccessTokenFromCode() {
        try {
            final Optional<String> possibleStravaCode = Optional.ofNullable(
                    exerciseTrackerClient.getStravaCode(persianServiceToken).execute().body());
            if (possibleStravaCode.isEmpty()) {
                System.out.println("Could not get a Strava token from exercise API");
                setAuthenticationStatus(false);
                return Optional.empty();
            }

            final Optional<AuthenticationToken> possibleAuthToken = getAuthenticationToken(possibleStravaCode.get());
            if (possibleAuthToken.isPresent()) {
                setAuthenticationStatus(true);
                updateStravaAuthTokens(possibleAuthToken.get());
                return possibleAuthToken.map(AuthenticationToken::getAccess_token);
            } else {
                System.out.println("Could not get a Strava token from given code");
                setAuthenticationStatus(true);
                return Optional.empty();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not make request to get Strava token from exercise API");
            setAuthenticationStatus(false);
            return Optional.empty();
        }
    }

    private void updateAccessTokenFromRefreshToken() {
        final Optional<AuthenticationToken> possibleNewAuthToken = credentialsHelper.getCredential(REFRESH_TOKEN).flatMap(this::getRefreshedToken);
        possibleNewAuthToken.ifPresent(this::updateStravaAuthTokens);
        setAuthenticationStatus(possibleNewAuthToken.isPresent());
    }

    private void updateStravaAuthTokens(final AuthenticationToken token) {
        credentialsHelper.putCredential(ACCESS_TOKEN, token.getAccess_token());
        credentialsHelper.putCredential(REFRESH_TOKEN, token.getRefresh_token());
    }

    private void setAuthenticationStatus(final boolean successful) {
        try {
            System.out.println("Auth status: " + Boolean.toString(successful));
            exerciseTrackerClient.setStravaAuthStatus(successful, persianServiceToken).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Optional<AuthenticationToken> getRefreshedToken(final String refreshToken) {
        try {
            return Optional.ofNullable(authenticationClient
                    .getRefreshAuthenticationToken(clientId, clientSecret, refreshToken, REFRESH_GRANT_TYPE)
                    .execute()
                    .body()
            );
        } catch (IOException e) {
            System.out.println("Could not make request to Strava API to get refreshed token");
            return Optional.empty();
        }
    }

    private Optional<AuthenticationToken> getAuthenticationToken(final String code) {
        try {
            return Optional.ofNullable(authenticationClient
                    .getAuthenticationToken(clientId, clientSecret, code, AUTH_GRANT_TYPE)
                    .execute()
                    .body());
        } catch (IOException e) {
            System.out.println("Could not make request to Strava API to get authentication token");
            return Optional.empty();
        }
    }

    public Optional<List<StravaRun>> getStravaRuns(final long epoch) {
        final Optional<String> accessToken = getAccessToken();

        final Optional<List<StravaRun>> possibleStravaRuns = accessToken.map(token -> {
            try {
                return summaryActivityClient
                        .getSummaryActivity("Bearer " + token, epoch)
                        .execute().body();
            } catch (final IOException e) {
                e.printStackTrace();
                return null;
            }
        });
        if (possibleStravaRuns.isEmpty() && accessToken.isPresent()) {
            updateAccessTokenFromRefreshToken();
        }
        return possibleStravaRuns;
    }
}
