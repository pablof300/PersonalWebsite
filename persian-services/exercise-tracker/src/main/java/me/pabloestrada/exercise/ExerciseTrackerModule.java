package me.pabloestrada.exercise;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import me.pabloestrada.credentials.CredentialsHelper;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.exercise.core.helpers.DateHelper;
import me.pabloestrada.exercise.onboarder.OnboarderApplication;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public final class ExerciseTrackerModule
        extends AbstractModule {
    @Override
    protected void configure() {
        bind(OkHttpClient.Builder.class).toInstance(getOkHTTPClient());
        bind(OnboarderApplication.class);
        bind(StravaClient.class).in(Singleton.class);
        bind(CredentialsHelper.class).in(Singleton.class);
        bind(DateHelper.class).in(Singleton.class);
    }

    private OkHttpClient.Builder getOkHTTPClient() {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient.addInterceptor(logging);
        return okHttpClient;
    }
}
