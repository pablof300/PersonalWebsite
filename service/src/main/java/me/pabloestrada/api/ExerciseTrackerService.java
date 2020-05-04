package me.pabloestrada.api;

import me.pabloestrada.api.dto.ExerciseSummaryDTO;

public abstract class ExerciseTrackerService
{
    public abstract ExerciseSummaryDTO getExerciseSummary();

    public abstract void addGymSession(float runningDistanceInMiles, int durationInMinutes);

    public abstract String getOAuthURL();

    public abstract boolean addStravaCode(String code);

    public abstract boolean getStravaStatus();
}
