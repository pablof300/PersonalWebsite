package me.pabloestrada.exercise.client;

import com.google.inject.Inject;
import me.pabloestrada.credentials.CredentialsHelper;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Optional;

public final class StravaClient {
    private static final String STRAVA_API_BASE_URL = "https://www.strava.com/";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String INVALID_KEY = "";

    private final AuthenticationClient authenticationClient;
    private final String clientId;
    private final String clientSecret;
    private final String code;

    @Inject
    public StravaClient(final OkHttpClient.Builder httpClient, final CredentialsHelper credentialsHelper) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(STRAVA_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        authenticationClient = retrofit.create(AuthenticationClient.class);
        clientId = credentialsHelper.getCredential("strava_client_id").orElse(INVALID_KEY);
        clientSecret = credentialsHelper.getCredential("strava_client_secret").orElse(INVALID_KEY);
        code = credentialsHelper.getCredential("strava_code").orElse(INVALID_KEY);
    }

    public Optional<String> getAuthorizationToken() {
        try {
            final Response<AuthenticationToken> authToken = authenticationClient
                    .getAuthenticationToken(clientId, clientSecret, code, GRANT_TYPE).execute();
            System.out.println(authToken.body());
            return Optional.ofNullable(authToken.body().getAccess_token());
        } catch (final IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
