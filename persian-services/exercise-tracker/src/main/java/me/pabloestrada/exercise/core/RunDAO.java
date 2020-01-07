package me.pabloestrada.exercise.core;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.pabloestrada.exercise.core.runs.ExerciseSummary;
import me.pabloestrada.exercise.core.runs.Run;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// TODO:
// Generalize DAO classes for Mongo (lib class)
// Refactor DAO to eliminate useless instance vars

public class RunDAO {
    private final static String DATABASE_NAME = "persian";
    private final static String RUNS_COLLECTION_NAME = "runs";
    private final static String UNPROCESSED_RUNS_COLLECTION_NAME = "crude_runs";
    private final static String EXERCISE_SUMMARY_COLLECTION_NAME = "exercise_summary";

    private final MongoCollection<Run> runsCollection;
    private final MongoCollection<Run> unprocessedRunsCollection;
    private final MongoCollection<ExerciseSummary> exerciseSummaryCollection;

    public RunDAO() {
        final CodecRegistry userCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        final MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(userCodecRegistry)
                .build();
        final MongoDatabase database = MongoClients.create(settings).getDatabase(DATABASE_NAME);
        runsCollection = database.getCollection(RUNS_COLLECTION_NAME, Run.class);
        unprocessedRunsCollection = database.getCollection(UNPROCESSED_RUNS_COLLECTION_NAME, Run.class);
        exerciseSummaryCollection = database.getCollection(EXERCISE_SUMMARY_COLLECTION_NAME, ExerciseSummary.class);
    }

    public List<Run> getAllUnprocessedRuns() {
        return StreamSupport
                .stream(unprocessedRunsCollection.find().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Run> getAllRuns() {
        return StreamSupport
                .stream(runsCollection.find().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void convertAllUnprocessedRuns(final List<Run> unprocessedRuns) {
        if (unprocessedRuns.isEmpty()) {
            return;
        }
        runsCollection.insertMany(unprocessedRuns);
        unprocessedRunsCollection.deleteMany(new Document());
    }

    public void insertExerciseSummary(final ExerciseSummary exerciseSummary) {
        // Check if it is empty, otherwise update it
        exerciseSummaryCollection.insertOne(exerciseSummary);
    }

//    public boolean verifyCredentials(final String username, final String password) {
//        return getUser(username).map(user -> user.getPassword().equals(password)).orElse(false);
//    }
//
//    public boolean insertUser(final String username, final String password) {
//        if (collection.find(eq("username", username)).first() != null) {
//            return false;
//        }
//        collection.insertOne(new User(username, password));
//        return true;
//    }
//
//    public boolean insertUser(final User user) {
//        if (collection.find(eq("username", user.getUsername())).first() != null) {
//            return false;
//        }
//        collection.insertOne(user);
//        return true;
//    }
//
//    public Optional<User> getUser(final String username) {
//        return Optional.ofNullable(collection.find(eq("username", username)).first());
//    }
}
