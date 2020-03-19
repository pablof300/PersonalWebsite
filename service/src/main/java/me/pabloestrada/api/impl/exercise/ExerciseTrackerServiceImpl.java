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
        return exerciseDAO
                .getExerciseSummary(LocalDate.now())
                .map(exerciseSummary -> new ExerciseSummaryDTO(
                        getLengthOfStreakInDaysFromToday(dateOfToday), exerciseSummary.getNumberOfMilesRan(),exerciseSummary.getNumberOfMinutesInGym()))
                .orElse(new ExerciseSummaryDTO(0,0,0));
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
        int lengthOfStreakInDays = 0;

        while (exerciseDAO
                .getExerciseSummary(
                        dateOfToday.minusDays(lengthOfStreakInDays)).map(ExerciseSummary::isSuccessfulDay)
                .orElse(false))
        {
            lengthOfStreakInDays++;
        }
        return lengthOfStreakInDays;
    }
}
