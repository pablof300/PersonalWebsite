package me.pabloestrada.exercise.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;

// TODO:
// Research more about Jackson serialization

public final class Run {
    private ObjectId id;

    @JsonProperty("startDate")
    private Date startDate;

    private float distanceInMeters;
    private int movingTimeInSeconds;
    private int elapsedTimeInSeconds;

    private float totalElevationGainInMeters;
    private float highestElevation;
    private float lowestElevation;

    private float startLatitude;
    private float endLatitude;
    private float startLongitude;
    private float endLongitude;


    private float averageSpeedInMetersPerSecond;
    private float maxSpeedInMetersPerSecond;

    private float kilojoulesBurned;

    public Run() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public float getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(float distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
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

    public float getHighestElevation() {
        return highestElevation;
    }

    public void setHighestElevation(float highestElevation) {
        this.highestElevation = highestElevation;
    }

    public float getLowestElevation() {
        return lowestElevation;
    }

    public void setLowestElevation(float lowestElevation) {
        this.lowestElevation = lowestElevation;
    }

    public float getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(float startLatitude) {
        this.startLatitude = startLatitude;
    }

    public float getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(float endLatitude) {
        this.endLatitude = endLatitude;
    }

    public float getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(float startLongitude) {
        this.startLongitude = startLongitude;
    }

    public float getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(float endLongitude) {
        this.endLongitude = endLongitude;
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

    public float getKilojoulesBurned() {
        return kilojoulesBurned;
    }

    public void setKilojoulesBurned(float kilojoulesBurned) {
        this.kilojoulesBurned = kilojoulesBurned;
    }
}
