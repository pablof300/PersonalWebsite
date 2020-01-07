package me.pabloestrada.exercise.onboarder;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.pabloestrada.exercise.ExerciseTrackerModule;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.exercise.core.RunThreshold;
import me.pabloestrada.exercise.core.helpers.DateHelper;
import me.pabloestrada.exercise.core.RunDAO;
import me.pabloestrada.exercise.core.runs.ExerciseSummary;
import me.pabloestrada.exercise.core.runs.Run;

import java.util.Date;
import java.util.List;

public final class StravaRunsOnboarder {
    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new ExerciseTrackerModule());
        final StravaClient stravaClient = injector.getInstance(StravaClient.class);
        final RunDAO runDAO = injector.getInstance(RunDAO.class);
        final DateHelper dateHelper = injector.getInstance(DateHelper.class);

        final List<Run> allUnprocessedRuns = runDAO.getAllUnprocessedRuns();
        final List<Run> allRuns = runDAO.getAllRuns();
        allRuns.addAll(allUnprocessedRuns);
        stravaClient.getAllSummaryActivities().ifPresent(allRuns::addAll);
        runDAO.convertAllUnprocessedRuns(allUnprocessedRuns);

        final ExerciseSummary emptyExerciseSummary = new ExerciseSummary(null, 0, 0);
        allRuns.sort((runOne, runTwo) -> {
            if (runOne.getStartDate().before(runTwo.getStartDate())) {
                return 1;
            } else if (runOne.getStartDate().after(runTwo.getStartDate())) {
                return -1;
            } else {
                return 0;
            }
        });

        if (allRuns.isEmpty()) {
            runDAO.insertExerciseSummary(emptyExerciseSummary);
            return;
        }
        Run lastRun = allRuns.get(0);
        final Date dateOfToday = new Date();
        final int daysBetweenLastRunAndToday = dateHelper.getDaysInBetweenRuns(lastRun.getStartDate(), dateOfToday);

        if (daysBetweenLastRunAndToday > 1) {
            runDAO.insertExerciseSummary(emptyExerciseSummary);
            return;
        }
        int longestStreak = 1;
        float milesRanToday = daysBetweenLastRunAndToday == 0 ? lastRun.getDistance() : 0;

        for (int day = 1; day < allRuns.size(); day++) {
            final Run currentRun = allRuns.get(day);
            if (dateHelper.getDaysInBetweenRuns(lastRun, currentRun) > 1) {
                break;
            }
            if (!currentRun.isSuccessfulStrengtheningExercise() || currentRun.getDistance() < RunThreshold.NUMBER_OF_METERS_FOR_SUCCESSFUL_RUN) {
                break;
            }
            if (dateHelper.getDaysInBetweenRuns(currentRun.getStartDate(), dateOfToday) == 0) {
                milesRanToday += currentRun.getDistance();
            }
            longestStreak++;
        }
        runDAO.insertExerciseSummary(new ExerciseSummary(allRuns.get(0).getStartDate(), longestStreak, milesRanToday));
    }
}
