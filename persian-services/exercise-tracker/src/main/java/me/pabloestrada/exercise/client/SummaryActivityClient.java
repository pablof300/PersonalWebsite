package me.pabloestrada.exercise.client;

import me.pabloestrada.exercise.core.runs.StravaRun;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.util.List;

public interface SummaryActivityClient {
    @GET("/api/v3/athlete/activities")
    public Call<List<StravaRun>> getSummaryActivity(@Header("Authorization") String token, @Query("after") long after);
}
