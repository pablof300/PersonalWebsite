package me.pabloestrada.api.dto;

import me.pabloestrada.exercise.core.exercise.GymSession;
import me.pabloestrada.exercise.core.exercise.StravaRun;

import java.util.List;

public class FullExerciseSummaryDTO extends ExerciseSummaryDTO {
    private List<StravaRun> stravaRuns;
    private List<GymSession> gymSessions;

    public FullExerciseSummaryDTO(int lengthOfStreakInDays, int milesRanToday, int numberOfMinutesInGym, List<StravaRun> stravaRuns, List<GymSession> gymSessions) {
        super(lengthOfStreakInDays, milesRanToday, numberOfMinutesInGym);
        this.stravaRuns = stravaRuns;
        this.gymSessions = gymSessions;
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
}
