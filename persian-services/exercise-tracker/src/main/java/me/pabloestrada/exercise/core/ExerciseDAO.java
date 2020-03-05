package me.pabloestrada.exercise.core;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.exercise.core.exercise.ExerciseSummary;
import me.pabloestrada.exercise.core.exercise.Exercise;
import me.pabloestrada.exercise.core.exercise.GymSession;
import me.pabloestrada.exercise.core.exercise.StravaRun;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


// TODO:
// Generalize DAO classes for Mongo (lib class)
// Refactor DAO to eliminate useless instance vars

public class ExerciseDAO {
    private final static String DATABASE_NAME = "persian";

    private final static String RUNS_COLLECTION_NAME = "run_sessions";
    private final static String GYM_SESSIONS_COLLECTION_NAME = "gym_sessions";
    private final static String EXERCISE_SUMMARY_COLLECTION_NAME = "exercise_summary";

    private final MongoCollection<StravaRun> runsCollection;
    private final MongoCollection<GymSession> gymSessionsCollection;
    private final MongoCollection<ExerciseSummary> exerciseSummaryCollection;

    public ExerciseDAO() {
        final CodecRegistry userCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        final MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(userCodecRegistry)
                .build();
        final MongoDatabase database = MongoClients.create(settings).getDatabase(DATABASE_NAME);
        runsCollection = database.getCollection(RUNS_COLLECTION_NAME, StravaRun.class);
        gymSessionsCollection = database.getCollection(GYM_SESSIONS_COLLECTION_NAME, GymSession.class);
        exerciseSummaryCollection = database.getCollection(EXERCISE_SUMMARY_COLLECTION_NAME, ExerciseSummary.class);
    }

    // Strava Runs
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
    }

    // Exercise Summary
    public void updateExerciseToSummary(final LocalDate date, Bson update) {
        final Bson findQuery = Filters.eq("date", date);
        if (exerciseSummaryCollection.find(findQuery).first() == null) {
            exerciseSummaryCollection.insertOne(new ExerciseSummary(date));
        }
        exerciseSummaryCollection.updateOne(findQuery, update);
    }

    public Optional<ExerciseSummary> getExerciseSummary(final LocalDate date) {
        final ExerciseSummary exerciseSummary = exerciseSummaryCollection.find(Filters.eq("date", date)).first();
        return exerciseSummary == null ? Optional.empty() : Optional.of(exerciseSummary);
    }

    // Gym exercise
    public void insertGymExercise(final float runningDistanceInMeters, final int durationInMinutes) {
        final GymSession gymExercise = new GymSession(runningDistanceInMeters, durationInMinutes);
        gymSessionsCollection.insertOne(gymExercise);
        updateExerciseToSummary(gymExercise.getStartDate().toLocalDate(), Updates.push("gymSessions", gymExercise));
    }
}
