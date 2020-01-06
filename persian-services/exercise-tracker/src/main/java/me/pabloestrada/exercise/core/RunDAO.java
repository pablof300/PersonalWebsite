package me.pabloestrada.exercise.core;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.pabloestrada.exercise.core.runs.Run;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

// TODO:
// Generalize DAO classes for Mongo (lib class)
// Refactor DAO to eliminate useless instance vars

public class RunDAO {
    private final static String DATABASE_NAME = "persian";
    private final static String RUNS_COLLECTION_NAME = "runs";
    private final static String EXERCISE_SUMMARY_COLLECTION_NAME = "exercise_summary";

    private final CodecRegistry userCodecRegistry;
    private final MongoClientSettings settings;
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    private final MongoCollection<Run> runsCollection;
    private final MongoCollection<Run> exerciseSummaryCollection;

    public RunDAO() {
        userCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        settings = MongoClientSettings.builder()
                .codecRegistry(userCodecRegistry)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(DATABASE_NAME);
        runsCollection = database.getCollection(RUNS_COLLECTION_NAME, Run.class);
        exerciseSummaryCollection = database.getCollection(EXERCISE_SUMMARY_COLLECTION_NAME, Run.class);
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
