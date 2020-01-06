package me.pabloestrada.exercise;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import me.pabloestrada.credentials.CredentialsHelper;
import me.pabloestrada.exercise.client.StravaClient;
import okhttp3.OkHttpClient;

public final class ExerciseTrackerModule
        extends AbstractModule {
    @Override
    protected void configure() {
        bind(OkHttpClient.Builder.class).toInstance(new OkHttpClient.Builder());
        bind(StravaClient.class).in(Singleton.class);
        bind(CredentialsHelper.class).in(Singleton.class);
    }
}
