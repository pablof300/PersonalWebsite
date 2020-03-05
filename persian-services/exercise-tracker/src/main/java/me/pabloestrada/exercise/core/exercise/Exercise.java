package me.pabloestrada.exercise.core.exercise;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

// TODO:
// Research more about Jackson serialization
// Constructor with parameters for POJOs

public abstract class Exercise {

    @SerializedName("start_date_local")
    private LocalDateTime startDate;

    @SerializedName("mongo_id")
    private ObjectId id;

    @SerializedName("distance")
    private float runningDistanceInMeters;

    private boolean isSuccessfulExercise;
    private int durationInMinutes;

    private ExerciseType typeOfExercise;

    Exercise(final ExerciseType typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    Exercise(final LocalDateTime startDate, final float runningDistanceInMeters, final int durationInMinutes, final ExerciseType typeOfExercise) {
        this(typeOfExercise);
        this.startDate = startDate;
        this.runningDistanceInMeters = runningDistanceInMeters;
        this.durationInMinutes = durationInMinutes;
    }

    public abstract void establishSuccessfulStatus();

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public float getRunningDistanceInMeters() {
        return runningDistanceInMeters;
    }

    public void setRunningDistanceInMeters(float runningDistanceInMeters) {
        this.runningDistanceInMeters = runningDistanceInMeters;
    }

    public ExerciseType getTypeOfExercise() {
        return typeOfExercise;
    }

    public void setTypeOfExercise(ExerciseType typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public boolean isSuccessfulExercise() {
        return isSuccessfulExercise;
    }

    public void setSuccessfulExercise(boolean successfulExercise) {
        isSuccessfulExercise = successfulExercise;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
