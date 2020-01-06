package me.pabloestrada.exercise.client;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthenticationClient {
    @POST("/oauth/token")
    Call<AuthenticationToken> getAuthenticationToken(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("code") String code,
            @Query("grant_type") String grantType
    );
}
