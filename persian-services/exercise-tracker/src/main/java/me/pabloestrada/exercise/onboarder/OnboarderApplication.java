package me.pabloestrada.exercise.onboarder;

import com.google.inject.Inject;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.exercise.core.ExerciseDAO;
import me.pabloestrada.exercise.core.helpers.DateHelper;
import me.pabloestrada.exercise.core.exercise.ExerciseSummary;
import me.pabloestrada.exercise.core.exercise.Exercise;

import java.util.Date;
import java.util.List;

public final class OnboarderApplication {

    private ExerciseDAO exerciseDAO;
    private StravaClient stravaClient;
    private DateHelper dateHelper;

    @Inject
    public OnboarderApplication(final ExerciseDAO exerciseDAO, final StravaClient stravaClient, final DateHelper dateHelper) {
        this.exerciseDAO = exerciseDAO;
        this.stravaClient = stravaClient;
        this.dateHelper = dateHelper;
    }

    public void execute() {
        final List<Exercise> allExercises = exerciseDAO.getAllRuns();
        stravaClient.getAllSummaryActivities().ifPresent(runs -> exerciseDAO.insertManyStravaRuns(runs));
        allExercises.addAll(exerciseDAO.getAllGymExercise());

        allExercises.sort((exerciseOne, exerciseTwo) -> {
            if (exerciseOne.getStartDate().before(exerciseTwo.getStartDate())) {
                return 1;
            } else if (exerciseOne.getStartDate().after(exerciseTwo.getStartDate())) {
                return -1;
            } else {
                return 0;
            }
        });

        if (allExercises.isEmpty()) {
            return;
        }
        final Exercise latestExercise = allExercises.get(0);
        Exercise previousExercise = latestExercise;
        final Date dateOfToday = new Date();
        final int daysBetweenLastRunAndToday = dateHelper.getDaysInBetweenRuns(previousExercise.getStartDate(), dateOfToday);

        if (daysBetweenLastRunAndToday > 1) {
            return;
        }
        int longestStreak = 1;
        float runningDistanceInMeters = daysBetweenLastRunAndToday == 0 ? previousExercise.getRunningDistanceInMeters() : 0;

        for (int day = 1; day < allExercises.size(); day++) {
            final Exercise currentExercise = allExercises.get(day);
            if (dateHelper.getDaysInBetweenRuns(previousExercise, currentExercise) > 1) {
                break;
            }
            if (!currentExercise.isSuccessfulExercise()) {
                break;
            }
            if (dateHelper.getDaysInBetweenRuns(currentExercise.getStartDate(), dateOfToday) == 0) {
                runningDistanceInMeters += currentExercise.getRunningDistanceInMeters();
            }
            previousExercise = currentExercise;
            longestStreak++;
        }

        exerciseDAO.updateExerciseSummary(new ExerciseSummary(
                dateOfToday,
                longestStreak,
                latestExercise.getStartDate(),
                runningDistanceInMeters,
                latestExercise.getDurationInMinutes(),
                latestExercise.getTypeOfExercise())
        );
    }
}
