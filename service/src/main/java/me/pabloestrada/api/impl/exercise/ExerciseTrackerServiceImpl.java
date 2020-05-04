package me.pabloestrada.api.impl.exercise;

import com.google.inject.Inject;
import me.pabloestrada.api.ExerciseTrackerService;
import me.pabloestrada.api.dto.ExerciseSummaryDTO;
import me.pabloestrada.core.CoreServiceConstants;
import me.pabloestrada.credentials.CredentialsHelper;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.exercise.core.ExerciseDAO;
import me.pabloestrada.exercise.core.exercise.ExerciseSummary;
import me.pabloestrada.exercise.core.helpers.MeasurementHelper;

import java.time.LocalDate;

public final class ExerciseTrackerServiceImpl
    extends ExerciseTrackerService
{
    private final static String BASE_STRAVA_URL = "http://www.strava.com/oauth/authorize";

    private final ExerciseDAO exerciseDAO;
    private final StravaClient stravaClient;
    private final CredentialsHelper credentialsHelper;

    @Inject
    public ExerciseTrackerServiceImpl(final ExerciseDAO exerciseDAO,
                                      final StravaClient stravaClient,
                                      final CredentialsHelper credentialsHelper)
    {
        this.credentialsHelper = credentialsHelper;
        this.exerciseDAO = exerciseDAO;
        this.stravaClient = stravaClient;
    }

    @Override
    public ExerciseSummaryDTO getExerciseSummary() {
        final LocalDate dateOfToday = LocalDate.now();
        final ExerciseSummary exerciseSummaryOfToday = exerciseDAO.getExerciseSummary(LocalDate.now());
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
    public boolean addStravaCode(final String code) {
        // TODO:
        // Add security to check it is a valid code
        if (code == null) {
            System.out.println("Code is null");
            return false;
        }
        if (getStravaStatus()) {
            System.out.println("Strava API has already been authenticated");
            return false;
        }
        stravaClient.getAuthenticationToken(code).ifPresent(token -> {
            System.out.println("Successfully added new auth token for Strava client! " + token);
            exerciseDAO.setAccessToken(token.getAccess_token());
            exerciseDAO.setRefreshToken(token.getRefresh_token());
        });
        return true;
    }

    @Override
    public String getOAuthURL() {
        return BASE_STRAVA_URL + "?client_id=" + credentialsHelper.getCredential("strava_client_id").orElseThrow()
                + "&response_type=code&approval_prompt=force&scope=activity:read_all" +
                "&redirect_uri=" + CoreServiceConstants.getHostURL() + "/api/exercise/strava-callback";
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
