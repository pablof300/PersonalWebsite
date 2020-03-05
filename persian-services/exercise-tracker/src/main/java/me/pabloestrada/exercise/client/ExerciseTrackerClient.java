package me.pabloestrada.exercise.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ExerciseTrackerClient {
    @GET("/exercise/strava")
    Call<String> getStravaCode(@Header("bearerAuth") String bearerAuth);

    @POST("/exercise/strava-status")
    Call<String> setStravaAuthStatus(@Query("status") boolean status, @Header("bearerAuth") String bearerAuth);
}
