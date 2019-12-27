package me.pabloestrada.core.user;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.not;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;

public class UserDAO {

    private final static String DATABASE_NAME = "persian";
    private final static String USER_COLLECTION_NAME = "users";

    private final CodecRegistry userCodecRegistry;
    private final MongoClientSettings settings;
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<User> collection;

    public UserDAO() {
        userCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        settings = MongoClientSettings.builder()
                .codecRegistry(userCodecRegistry)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(DATABASE_NAME);
        collection = database.getCollection(USER_COLLECTION_NAME, User.class);
    }

    public boolean insertUser(final String username, final String password) {
        if (collection.find(eq("username", username)).first() != null) {
            return false;
        }
        collection.insertOne(new User(username, password));
        return true;
    }

    public boolean insertUser(final User user) {
        if (collection.find(eq("username", user.getUsername())).first() != null) {
            return false;
        }
        collection.insertOne(user);
        return true;
    }

    public Optional<User> getUser(final String username) {
        return Optional.ofNullable(collection.find(eq("username", username)).first());
    }
}