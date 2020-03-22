package me.pabloestrada.api.impl.exercise;

import com.google.inject.Inject;
import me.pabloestrada.api.ExerciseTrackerService;
import me.pabloestrada.api.dto.ExerciseSummaryDTO;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.exercise.core.ExerciseDAO;
import me.pabloestrada.exercise.core.exercise.ExerciseSummary;
import me.pabloestrada.exercise.core.helpers.MeasurementHelper;

import java.time.LocalDate;

public final class ExerciseTrackerServiceImpl
    extends ExerciseTrackerService
{
    private final ExerciseDAO exerciseDAO;
    private final StravaClient stravaClient;

    @Inject
    public ExerciseTrackerServiceImpl(final ExerciseDAO exerciseDAO, final StravaClient stravaClient) {
        this.exerciseDAO = exerciseDAO;
        this.stravaClient = stravaClient;
    }

    @Override
    public ExerciseSummaryDTO getExerciseSummary() {
        final LocalDate dateOfToday = LocalDate.now();
        final ExerciseSummary exerciseSummaryOfToday = exerciseDAO.getExerciseSummary(LocalDate.now());
        System.out.println("SUMMARY ");
        System.out.println(exerciseSummaryOfToday.getNumberOfMilesRan());
        System.out.println(exerciseSummaryOfToday.getStravaRuns());
        return new ExerciseSummaryDTO(
                getLengthOfStreakInDaysFromToday(dateOfToday),
                exerciseSummaryOfToday.getNumberOfMilesRan(),
                exerciseSummaryOfToday.getNumberOfMinutesInGym()
        );
    }

    @Override
    public void addGymSession(float runningDistanceInMiles, int durationInMinutes) {
        exerciseDAO.insertGymExercise(MeasurementHelper.convertMilesToMeters(runningDistanceInMiles), durationInMinutes);
    }

    @Override
    public void addStravaCode(final String code) {
        if (code == null) {
            return;
        }
        stravaClient.getAuthenticationToken(code).ifPresent(token -> {
            System.out.println("Successfully added new auth token for Strava client! " + token);
            exerciseDAO.setAccessToken(token.getAccess_token());
            exerciseDAO.setRefreshToken(token.getRefresh_token());
        });
    }

    @Override
    public boolean getStravaStatus() {
        return exerciseDAO.getExerciseCredentials().isValid();
    }

    private int getLengthOfStreakInDaysFromToday(final LocalDate dateOfToday) {
        final boolean isTodaySuccessful = exerciseDAO.getExerciseSummary(dateOfToday.minusDays(0)).isSuccessfulDay();
        int lengthOfStreakInDays = 1;

        while (exerciseDAO.getExerciseSummary(dateOfToday.minusDays(lengthOfStreakInDays)).isSuccessfulDay()) {
            System.out.println("Successful streak for " + dateOfToday.minusDays(lengthOfStreakInDays));
            System.out.println("Miles " + exerciseDAO.getExerciseSummary(dateOfToday.minusDays(lengthOfStreakInDays)).getNumberOfMilesRan());
            lengthOfStreakInDays++;
        }
        return lengthOfStreakInDays - (isTodaySuccessful ? 0 : 1);
    }
}
