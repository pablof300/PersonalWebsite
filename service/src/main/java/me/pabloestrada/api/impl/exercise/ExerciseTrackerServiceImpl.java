package me.pabloestrada.api.impl.exercise;

import com.google.inject.Inject;
import me.pabloestrada.api.ExerciseTrackerService;
import me.pabloestrada.api.dto.ExerciseSummaryDTO;
import me.pabloestrada.exercise.core.ExerciseDAO;
import me.pabloestrada.exercise.core.exercise.ExerciseSummary;
import me.pabloestrada.exercise.core.helpers.MeasurementHelper;

import java.time.LocalDate;
import java.util.Optional;

public final class ExerciseTrackerServiceImpl
    extends ExerciseTrackerService
{
    private ExerciseDAO exerciseDAO;
    private Optional<String> stravaCode;
    private boolean isAuthenticated;

    @Inject
    public ExerciseTrackerServiceImpl(final ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
        this.stravaCode = Optional.empty();
        this.isAuthenticated = false;
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
    public String getStravaCode() {
        return stravaCode.orElse(null);
    }

    @Override
    public void addStravaCode(final String code) {
        if (code == null) {
            return;
        }
        stravaCode = Optional.of(code);
    }

    @Override
    public void setStravaStatus(final boolean status) {
        isAuthenticated = status;
    }

    @Override
    public boolean getStravaStatus() {
        return isAuthenticated;
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
