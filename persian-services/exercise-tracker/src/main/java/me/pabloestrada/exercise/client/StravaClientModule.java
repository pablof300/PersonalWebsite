package me.pabloestrada.exercise.client;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public final class StravaClientModule
        extends AbstractModule {
    @Override
    protected void configure() {
        bind(OkHttpClient.Builder.class).toInstance(getOkHTTPClient());
        bind(StravaClient.class).in(Singleton.class);
    }

    private OkHttpClient.Builder getOkHTTPClient() {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient.addInterceptor(logging);
        return okHttpClient;
    }
}
