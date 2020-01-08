package me.pabloestrada.exercise.core.exercise;

import java.util.Date;

public final class ExerciseSummary {
    private Date lastTimeOfSummaryUpdate;
    private int numberOfDaysInStreak;

    // Make an object of LastExercise
    private Date lastTimeExercise;
    private float distanceRanInMiles;
    private int durationOfExercise;
    private ExerciseType typeOfExercise;

    public ExerciseSummary(Date lastTimeOfSummaryUpdate, int numberOfDaysInStreak, Date lastTimeExercise, float distanceRanInMiles, int durationOfExercise, ExerciseType typeOfExercise) {
        this.lastTimeOfSummaryUpdate = lastTimeOfSummaryUpdate;
        this.numberOfDaysInStreak = numberOfDaysInStreak;
        this.lastTimeExercise = lastTimeExercise;
        this.distanceRanInMiles = distanceRanInMiles;
        this.durationOfExercise = durationOfExercise;
        this.typeOfExercise = typeOfExercise;
    }

    public Date getLastTimeOfSummaryUpdate() {
        return lastTimeOfSummaryUpdate;
    }

    public void setLastTimeOfSummaryUpdate(Date lastTimeOfSummaryUpdate) {
        this.lastTimeOfSummaryUpdate = lastTimeOfSummaryUpdate;
    }

    public int getNumberOfDaysInStreak() {
        return numberOfDaysInStreak;
    }

    public void setNumberOfDaysInStreak(int numberOfDaysInStreak) {
        this.numberOfDaysInStreak = numberOfDaysInStreak;
    }

    public Date getLastTimeExercise() {
        return lastTimeExercise;
    }

    public void setLastTimeExercise(Date lastTimeExercise) {
        this.lastTimeExercise = lastTimeExercise;
    }

    public float getDistanceRanInMiles() {
        return distanceRanInMiles;
    }

    public void setDistanceRanInMiles(float distanceRanInMiles) {
        this.distanceRanInMiles = distanceRanInMiles;
    }

    public int getDurationOfExercise() {
        return durationOfExercise;
    }

    public void setDurationOfExercise(int durationOfExercise) {
        this.durationOfExercise = durationOfExercise;
    }

    public ExerciseType getTypeOfExercise() {
        return typeOfExercise;
    }

    public void setTypeOfExercise(ExerciseType typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }
}
