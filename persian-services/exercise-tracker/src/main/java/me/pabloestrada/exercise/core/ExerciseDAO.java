package me.pabloestrada.exercise.core;

import com.google.inject.Inject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import me.pabloestrada.database.PersianDatabase;
import me.pabloestrada.exercise.core.exercise.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


// TODO:
// Generalize DAO classes for Mongo (lib class)

public class ExerciseDAO {
    // TODO:
    // Abstract into other DAOs
    private final MongoCollection<StravaRun> runsCollection;
    private final MongoCollection<GymSession> gymSessionsCollection;
    private final MongoCollection<ExerciseSummary> exerciseSummaryCollection;
    private final MongoCollection<ExerciseCredentials> exerciseCredentialsCollection;

    @Inject
    public ExerciseDAO(final PersianDatabase persianDatabase) {
        runsCollection = persianDatabase.getDatabase()
                .getCollection(ExerciseDatabaseConstants.RUNS_COLLECTION_NAME, StravaRun.class);
        gymSessionsCollection = persianDatabase.getDatabase()
                .getCollection(ExerciseDatabaseConstants.GYM_SESSIONS_COLLECTION_NAME, GymSession.class);
        exerciseSummaryCollection = persianDatabase.getDatabase()
                .getCollection(ExerciseDatabaseConstants.EXERCISE_SUMMARY_COLLECTION_NAME, ExerciseSummary.class);
        exerciseCredentialsCollection = persianDatabase.getDatabase()
                .getCollection(ExerciseDatabaseConstants.EXERCISE_CREDENTIALS_COLLECTION_NAME, ExerciseCredentials.class);
    }

    // Strava Runs
    public boolean hasStravaRuns() {
        return runsCollection.find(new Document()).first() != null;
    }

    private boolean containsStravaRun(final StravaRun stravaRun) {
        final Document runDocument = new Document();
        runDocument.put("stravaId", stravaRun.getStravaId());
        return runsCollection.find(runDocument).first() != null;
    }

    public void insertManyStravaRuns(final List<StravaRun> runs) {
        runs.forEach(run -> {
           if (!containsStravaRun(run)) {
               run.establishSuccessfulStatus();
               runsCollection.insertOne(run);
               updateExerciseToSummary(run.getStartDate().toLocalDate(), Updates.push("stravaRuns", run));
           }
        });
        createEmptyExerciseSummary(LocalDate.now());
    }

    private ExerciseSummary createEmptyExerciseSummary(final LocalDate date) {
        return Optional.ofNullable(exerciseSummaryCollection.find(Filters.eq("date", date)).first())
            .orElseGet(() -> {
                final ExerciseSummary newExerciseSummary = new ExerciseSummary(date);
                exerciseSummaryCollection.insertOne(newExerciseSummary);
                return newExerciseSummary;
            });
    }

    // Exercise Summary
    private void updateExerciseToSummary(final LocalDate date, Bson update) {
        final Bson findQuery = Filters.eq("date", date);
        if (exerciseSummaryCollection.find(findQuery).first() == null) {
            createEmptyExerciseSummary(date);
        }
        exerciseSummaryCollection.updateOne(findQuery, update);
    }

    public ExerciseSummary getExerciseSummary(final LocalDate date) {
        return Optional
                .ofNullable(
                    exerciseSummaryCollection.find(Filters.eq("date", date)).first())
                .orElse(createEmptyExerciseSummary(date));
    }

    // Gym exercise
    public void insertGymExercise(final float runningDistanceInMeters, final int durationInMinutes) {
        final GymSession gymExercise = new GymSession(runningDistanceInMeters, durationInMinutes);
        gymSessionsCollection.insertOne(gymExercise);
        updateExerciseToSummary(gymExercise.getStartDate().toLocalDate(), Updates.push("gymSessions", gymExercise));
    }

    //Credentials
    public ExerciseCredentials getExerciseCredentials() {
        return Optional.ofNullable(exerciseCredentialsCollection.find().first())
                    .orElseGet(() -> {
                        final ExerciseCredentials emptyExerciseCredentials = new ExerciseCredentials(null, null);
                        exerciseCredentialsCollection.insertOne(emptyExerciseCredentials);
                        return emptyExerciseCredentials;
                    });
    }

    public void clearExerciseCredentials() {
        exerciseCredentialsCollection.deleteOne(new Document());
    }

    public Optional<String> getAccessToken() {
        return Optional.ofNullable(getExerciseCredentials().getAccessToken());
    }

    public void setAccessToken(final String accessToken) {
        exerciseCredentialsCollection.updateOne(
                Filters.eq("_id", getExerciseCredentials().getId()), Updates.set("accessToken", accessToken));
    }


    public Optional<String> getRefreshToken() {
        return Optional.ofNullable(getExerciseCredentials().getRefreshToken());
    }

    public void setRefreshToken(final String refreshToken) {
        exerciseCredentialsCollection.updateOne(
                Filters.eq("_id", getExerciseCredentials().getId()), Updates.set("refreshToken", refreshToken));
    }
}
