package me.pabloestrada.exercise.stravajob;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.mongodb.ConnectionString;
import me.pabloestrada.credentials.CredentialsHelper;
import me.pabloestrada.database.PersianDatabase;
import me.pabloestrada.exercise.client.StravaClientModule;

public class TestTrackerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConnectionString.class).toInstance(new ConnectionString("mongodb://localhost:27017"));
        bind(PersianDatabase.class).in(Singleton.class);
        bind(CredentialsHelper.class).in(Singleton.class);
        install(new StravaClientModule());
    }
}
