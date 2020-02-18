package me.pabloestrada.core.user;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import me.pabloestrada.core.Database;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public final class UserDAO
{
    private final MongoCollection<User> userCollection;

    public UserDAO() {
        final CodecRegistry userCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        final MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(userCodecRegistry)
                .build();
        userCollection = MongoClients.create(settings)
                .getDatabase(Database.DATABASE_NAME)
                .getCollection(Database.USER_COLLECTION_NAME, User.class);
    }

    public boolean verifyCredentials(final String username, final String password) {
        return getUser(username).map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public boolean insertUser(final String username, final String password) {
        if (userCollection.find(eq("username", username)).first() != null) {
            return false;
        }
        userCollection.insertOne(new User(username, password));
        return true;
    }

    public boolean insertUser(final User user) {
        if (userCollection.find(eq("username", user.getUsername())).first() != null) {
            return false;
        }
        userCollection.insertOne(user);
        return true;
    }

    public Optional<User> getUser(final String username) {
        return Optional.ofNullable(userCollection.find(eq("username", username)).first());
    }
}
