package me.pabloestrada.exercise.core.runs;

import java.util.Date;

public final class ExerciseSummary {
    private Date lastTimeExercise;
    private int numberOfDaysInStreak;
    private float milesRanToday;

    public ExerciseSummary(final Date lastTimeExercise, final int numberOfDaysInStreak, final float milesRanToday) {
        this.lastTimeExercise = lastTimeExercise;
        this.numberOfDaysInStreak = numberOfDaysInStreak;
        this.milesRanToday = milesRanToday;
    }

    public Date getLastTimeExercise() {
        return lastTimeExercise;
    }

    public void setLastTimeExercise(Date lastTimeExercise) {
        this.lastTimeExercise = lastTimeExercise;
    }

    public int getNumberOfDaysInStreak() {
        return numberOfDaysInStreak;
    }

    public void setNumberOfDaysInStreak(int numberOfDaysInStreak) {
        this.numberOfDaysInStreak = numberOfDaysInStreak;
    }

    public float getMilesRanToday() {
        return milesRanToday;
    }

    public void setMilesRanToday(float milesRanToday) {
        this.milesRanToday = milesRanToday;
    }
}
