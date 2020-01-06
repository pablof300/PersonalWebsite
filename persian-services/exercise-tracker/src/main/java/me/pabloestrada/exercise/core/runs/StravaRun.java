package me.pabloestrada.exercise.core.runs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class StravaRun
        extends Run {
    @SerializedName("id")
    private long stravaId;

    @SerializedName("moving_time")
    private int movingTimeInSeconds;

    @SerializedName("elapsed_time")
    private int elapsedTimeInSeconds;

    @SerializedName("total_elevation_gain")
    private float totalElevationGainInMeters;

    @SerializedName("elev_high")
    private float elevationHighInMeters;

    @SerializedName("elev_low")
    private float elevationLowInMeters;

    @SerializedName("start_latlng")
    private List<Float> startLatitudeAndLongitude;

    @SerializedName("end_latlng")
    private List<Float> endLatitudeAndLongitude;

    @SerializedName("average_speed")
    private float averageSpeedInMetersPerSecond;

    @SerializedName("max_speed")
    private float maxSpeedInMetersPerSecond;

    @SerializedName("kilojoules")
    private float kilojoules;

    public StravaRun() {
        super(RunType.STRAVA);
    }

    public long getStravaId() {
        return stravaId;
    }

    public void setStravaId(long stravaId) {
        this.stravaId = stravaId;
    }

    public int getMovingTimeInSeconds() {
        return movingTimeInSeconds;
    }

    public void setMovingTimeInSeconds(int movingTimeInSeconds) {
        this.movingTimeInSeconds = movingTimeInSeconds;
    }

    public int getElapsedTimeInSeconds() {
        return elapsedTimeInSeconds;
    }

    public void setElapsedTimeInSeconds(int elapsedTimeInSeconds) {
        this.elapsedTimeInSeconds = elapsedTimeInSeconds;
    }

    public float getTotalElevationGainInMeters() {
        return totalElevationGainInMeters;
    }

    public void setTotalElevationGainInMeters(float totalElevationGainInMeters) {
        this.totalElevationGainInMeters = totalElevationGainInMeters;
    }

    public float getElevationHighInMeters() {
        return elevationHighInMeters;
    }

    public void setElevationHighInMeters(float elevationHighInMeters) {
        this.elevationHighInMeters = elevationHighInMeters;
    }

    public float getElevationLowInMeters() {
        return elevationLowInMeters;
    }

    public void setElevationLowInMeters(float elevationLowInMeters) {
        this.elevationLowInMeters = elevationLowInMeters;
    }

    public List<Float> getStartLatitudeAndLongitude() {
        return startLatitudeAndLongitude;
    }

    public void setStartLatitudeAndLongitude(List<Float> startLatitudeAndLongitude) {
        this.startLatitudeAndLongitude = startLatitudeAndLongitude;
    }

    public List<Float> getEndLatitudeAndLongitude() {
        return endLatitudeAndLongitude;
    }

    public void setEndLatitudeAndLongitude(List<Float> endLatitudeAndLongitude) {
        this.endLatitudeAndLongitude = endLatitudeAndLongitude;
    }

    public float getAverageSpeedInMetersPerSecond() {
        return averageSpeedInMetersPerSecond;
    }

    public void setAverageSpeedInMetersPerSecond(float averageSpeedInMetersPerSecond) {
        this.averageSpeedInMetersPerSecond = averageSpeedInMetersPerSecond;
    }

    public float getMaxSpeedInMetersPerSecond() {
        return maxSpeedInMetersPerSecond;
    }

    public void setMaxSpeedInMetersPerSecond(float maxSpeedInMetersPerSecond) {
        this.maxSpeedInMetersPerSecond = maxSpeedInMetersPerSecond;
    }

    public float getKilojoules() {
        return kilojoules;
    }

    public void setKilojoules(float kilojoules) {
        this.kilojoules = kilojoules;
    }

    @Override
    public String toString() {
        return "StravaRun{" +
                "distance=" + getDistance() + ", " +
                "stravaId=" + stravaId +
                ", movingTimeInSeconds=" + movingTimeInSeconds +
                ", elapsedTimeInSeconds=" + elapsedTimeInSeconds +
                ", totalElevationGainInMeters=" + totalElevationGainInMeters +
                ", elevationHighInMeters=" + elevationHighInMeters +
                ", elevationLowInMeters=" + elevationLowInMeters +
                ", startLatitudeAndLongitude=" + startLatitudeAndLongitude +
                ", endLatitudeAndLongitude=" + endLatitudeAndLongitude +
                ", averageSpeedInMetersPerSecond=" + averageSpeedInMetersPerSecond +
                ", maxSpeedInMetersPerSecond=" + maxSpeedInMetersPerSecond +
                ", kilojoules=" + kilojoules +
                '}';
    }
}
