package me.pabloestrada.exercise.core.exercise;

import me.pabloestrada.exercise.core.helpers.MeasurementHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class ExerciseSummary {
    private LocalDate date;
    private List<StravaRun> stravaRuns;
    private List<GymSession> gymSessions;

    public ExerciseSummary() {
    }

    public ExerciseSummary(final LocalDate date) {
        this.date = date;
        this.stravaRuns = new ArrayList<StravaRun>();
        this.gymSessions = new ArrayList<GymSession>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<StravaRun> getStravaRuns() {
        return stravaRuns;
    }

    public void setStravaRuns(List<StravaRun> stravaRuns) {
        this.stravaRuns = stravaRuns;
    }

    public List<GymSession> getGymSessions() {
        return gymSessions;
    }

    public void setGymSessions(List<GymSession> gymSessions) {
        this.gymSessions = gymSessions;
    }

    public boolean isSuccessfulDay() {
        return stravaRuns.stream().anyMatch(Exercise::isSuccessfulExercise) || gymSessions.stream().anyMatch(Exercise::isSuccessfulExercise);
    }

    public float getNumberOfMilesRan() {
        double numberOfMetersRan = stravaRuns.stream().mapToDouble(Exercise::getRunningDistanceInMeters).sum()
                + gymSessions.stream().mapToDouble(Exercise::getRunningDistanceInMeters).sum();
        return MeasurementHelper.convertMetersToMiles(numberOfMetersRan);
    }

    public int getNumberOfMinutesInGym() {
        return gymSessions
                .stream()
                .mapToInt(Exercise::getDurationInMinutes).sum();
    }
}
