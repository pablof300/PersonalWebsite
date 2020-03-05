package me.pabloestrada.exercise.core.exercise;

import me.pabloestrada.exercise.core.ExerciseThresholds;

import java.time.LocalDateTime;

public final class GymSession
        extends Exercise {

    public GymSession() {
        super(ExerciseType.GYM);
    }

    public GymSession(final float runningDistanceInMeters, final int durationInMinutes) {
        super(LocalDateTime.now(), runningDistanceInMeters, durationInMinutes, ExerciseType.GYM);
    }

    @Override
    public void establishSuccessfulStatus() {
        setSuccessfulExercise(getDurationInMinutes() > ExerciseThresholds.NUMBER_OF_MINUTES_FOR_SUCCESSFUL_GYM_SESSION);
    }
}
