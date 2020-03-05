package me.pabloestrada.api;

import me.pabloestrada.api.dto.ExerciseSummaryDTO;

public abstract class ExerciseTrackerService
{
    public abstract ExerciseSummaryDTO getExerciseSummary();

    public abstract void addGymSession(float runningDistanceInMiles, int durationInMinutes);

    public abstract String getStravaCode();

    public abstract void addStravaCode(String code);

    public abstract void setStravaStatus(boolean status);

    public abstract boolean getStravaStatus();
}
