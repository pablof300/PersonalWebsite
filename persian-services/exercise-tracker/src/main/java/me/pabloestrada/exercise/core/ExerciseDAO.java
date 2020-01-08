package me.pabloestrada.exercise.core;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.pabloestrada.exercise.core.exercise.ExerciseSummary;
import me.pabloestrada.exercise.core.exercise.Exercise;
import me.pabloestrada.exercise.core.exercise.GymSession;
import me.pabloestrada.exercise.core.exercise.StravaRun;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// TODO:
// Generalize DAO classes for Mongo (lib class)
// Refactor DAO to eliminate useless instance vars

public class ExerciseDAO {
    private final static String DATABASE_NAME = "persian";

    private final static String RUNS_COLLECTION_NAME = "runs";
    private final static String GYM_SESSIONS_COLLECTION_NAME = "gym_sessions";
    private final static String UNPROCESSED_GYM_SESSION_COLLECTION_NAME = "unprocessed_gym_sessions";
    private final static String EXERCISE_SUMMARY_COLLECTION_NAME = "exercise_summary";

    private final MongoCollection<Exercise> runsCollection;
    private final MongoCollection<GymSession> gymSessionsCollection;
    private final MongoCollection<GymSession> unprocessedGymSessionsCollection;
    private final MongoCollection<ExerciseSummary> exerciseSummaryCollection;

    public ExerciseDAO() {
        final CodecRegistry userCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        final MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(userCodecRegistry)
                .build();
        final MongoDatabase database = MongoClients.create(settings).getDatabase(DATABASE_NAME);
        runsCollection = database.getCollection(RUNS_COLLECTION_NAME, Exercise.class);
        gymSessionsCollection = database.getCollection(GYM_SESSIONS_COLLECTION_NAME, GymSession.class);
        unprocessedGymSessionsCollection = database.getCollection(UNPROCESSED_GYM_SESSION_COLLECTION_NAME, GymSession.class);
        exerciseSummaryCollection = database.getCollection(EXERCISE_SUMMARY_COLLECTION_NAME, ExerciseSummary.class);
    }

    // Strava Runs

    private boolean containsStravaRun(final StravaRun stravaRun) {
        final Document runDocument = new Document();
        runDocument.put("stravaId", stravaRun.getStravaId());
        return runsCollection.find(runDocument).first() != null;
    }

    public List<Exercise> getAllRuns() {
        return StreamSupport
                .stream(runsCollection.find().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void insertManyStravaRuns(final List<StravaRun> runs) {
       runs.forEach(run -> {
           if (!containsStravaRun(run)) {
               run.establishSuccessfulStatus();
               runsCollection.insertOne(run);
           }
       });
    }

    // Exercise Summary

    public void updateExerciseSummary(final ExerciseSummary exerciseSummary) {
        if (exerciseSummaryCollection.countDocuments() == 0) {
            exerciseSummaryCollection.insertOne(exerciseSummary);
        } else {
            final Document updatedExerciseSummaryData = new Document();
            updatedExerciseSummaryData.put("lastTimeOfSummaryUpdate", exerciseSummary.getLastTimeOfSummaryUpdate());
            updatedExerciseSummaryData.put("lastTimeExercise", exerciseSummary.getLastTimeExercise());
            updatedExerciseSummaryData.put("numberOfDaysInStreak", exerciseSummary.getNumberOfDaysInStreak());
            updatedExerciseSummaryData.put("milesRanToday", exerciseSummary.getDistanceRanInMiles());

            exerciseSummaryCollection.findOneAndUpdate(new Document(), updatedExerciseSummaryData);
        }
    }

    public Optional<ExerciseSummary> getExerciseSummary() {
        final ExerciseSummary exerciseSummary = exerciseSummaryCollection.find().first();
        if (exerciseSummary != null) {
            return Optional.of(exerciseSummary);
        }
        return Optional.empty();
    }

    public boolean isExerciseSummaryCollectionEmpty() {
        return exerciseSummaryCollection.countDocuments() == 0;
    }

    // Gym exercise

    public List<GymSession> getAllGymExercise() {
        return StreamSupport
                .stream(gymSessionsCollection.find().spliterator(), false)
                .collect(Collectors.toList());
    }


//    public void convertAllUnprocessedRuns(final List<Exercise> unprocessedExercises) {
//        if (unprocessedExercises.isEmpty()) {
//            return;
//        }
//        runsCollection.insertMany(unprocessedExercises);
//        unprocessedRunsCollection.deleteMany(new Document());
//    }
//
//    public List<Exercise> getAllUnprocessedRuns() {
//        return StreamSupport
//                .stream(unprocessedRunsCollection.find().spliterator(), false)
//                .collect(Collectors.toList());
//    }
}
