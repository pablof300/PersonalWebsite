package me.pabloestrada.exercise.stravajob;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import me.pabloestrada.credentials.CredentialsHelper;
import me.pabloestrada.exercise.client.StravaClientModule;

public final class StravaRunTrackerModule
        extends AbstractModule {
    @Override
    protected void configure() {
        bind(CredentialsHelper.class).in(Singleton.class);
        install(new StravaClientModule());
    }
}
