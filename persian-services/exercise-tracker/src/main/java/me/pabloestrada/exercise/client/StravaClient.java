package me.pabloestrada.exercise.client;

import com.google.gson.*;
import com.google.inject.Inject;
import me.pabloestrada.credentials.CredentialsHelper;
import me.pabloestrada.exercise.core.ExerciseDAO;
import me.pabloestrada.exercise.core.exercise.StravaRun;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public final class StravaClient {
    private static final String STRAVA_API_BASE_URL = "https://www.strava.com/";

    // TODO:
    // Create library for all these constants
    private static final String AUTH_GRANT_TYPE = "authorization_code";
    private static final String REFRESH_GRANT_TYPE = "refresh_token";
    private static final String INVALID_KEY = "";

    private final AuthenticationClient authenticationClient;
    private final SummaryActivityClient summaryActivityClient;

    private final String clientId;
    private final String clientSecret;

    private final ExerciseDAO exerciseDAO;

    // TODO
    // Convert StravaClient into it's own library
    @Inject
    public StravaClient(final OkHttpClient.Builder httpClient, final CredentialsHelper credentialsHelper, final ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;

        final Retrofit stravaRetrofitBuilder = getStravaRetrofitBuilder(httpClient);
        summaryActivityClient = stravaRetrofitBuilder.create(SummaryActivityClient.class);
        authenticationClient = stravaRetrofitBuilder.create(AuthenticationClient.class);

        clientId = credentialsHelper.getCredential("strava_client_id").orElse(INVALID_KEY);
        clientSecret = credentialsHelper.getCredential("strava_client_secret").orElse(INVALID_KEY);
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

    private Optional<String> getAccessToken() {
        final Optional<String> possibleAccessToken = exerciseDAO.getAccessToken();
        return possibleAccessToken.isPresent() ? possibleAccessToken : updateAccessTokenFromRefreshToken();
    }

    private Optional<String> updateAccessTokenFromRefreshToken() {
        final Optional<String> possibleAccessToken = exerciseDAO.getRefreshToken().flatMap(this::getRefreshedToken)
                .map(authToken -> {
                    updateStravaAuthTokens(authToken);
                    return authToken.getAccess_token();
                });
        System.out.println(
                possibleAccessToken.isPresent() ? "Successfully refresh access token from refresh token!" : "Failed to refresh access token!");
        return possibleAccessToken;
    }

    private void updateStravaAuthTokens(final AuthenticationToken token) {
        exerciseDAO.setAccessToken(token.getAccess_token());
        exerciseDAO.setRefreshToken(token.getRefresh_token());
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

    public Optional<AuthenticationToken> getAuthenticationToken(final String code) {
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

        return accessToken.map(token -> {
            try {
                return summaryActivityClient
                        .getSummaryActivity("Bearer " + token, epoch)
                        .execute().body();
            } catch (final IOException e) {
                exerciseDAO.clearExerciseCredentials();
                e.printStackTrace();
                return null;
            }
        });
    }
}
