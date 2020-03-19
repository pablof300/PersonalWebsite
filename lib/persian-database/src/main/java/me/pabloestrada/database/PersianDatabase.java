package me.pabloestrada.database;

import com.google.inject.Inject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public final class PersianDatabase {
    private final MongoDatabase database;

    @Inject
    public PersianDatabase(final ConnectionString connectionString) {
        final CodecRegistry userCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        final MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(userCodecRegistry)
                .applyConnectionString(connectionString)
                .build();
        database = MongoClients.create(settings).getDatabase(PersianDatabaseConstants.DATABASE_NAME);
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
