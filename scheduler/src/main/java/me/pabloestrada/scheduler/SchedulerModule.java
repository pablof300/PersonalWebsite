package me.pabloestrada.scheduler;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.mongodb.ConnectionString;
import me.pabloestrada.database.PersianDatabase;
import me.pabloestrada.exercise.stravajob.StravaRunTrackerModule;
import net.halflite.guicequartzsample.config.ConfigModule;

import java.util.Optional;

final class SchedulerModule
        extends AbstractModule
{
    @Override
    protected void configure() {
        bind(ConnectionString.class).toInstance(getConnectionString());
        bind(PersianDatabase.class).in(Singleton.class);
        install(new ConfigModule());
        install(new StravaRunTrackerModule());
    }

    private ConnectionString getConnectionString() {
        final Optional<String> rawConnectionString = Optional.ofNullable(System.getProperty("databaseURI"));
        return new ConnectionString(rawConnectionString.orElse("mongodb://localhost:27017"));
    }
}