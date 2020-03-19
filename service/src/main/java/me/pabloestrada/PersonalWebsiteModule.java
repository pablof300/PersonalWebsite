package me.pabloestrada;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.mongodb.ConnectionString;
import me.pabloestrada.api.AuthenticationService;
import me.pabloestrada.api.ExerciseTrackerService;
import me.pabloestrada.api.PersonalWebsiteService;
import me.pabloestrada.api.impl.authentication.AuthenticationServiceImpl;
import me.pabloestrada.api.impl.exercise.ExerciseTrackerServiceImpl;
import me.pabloestrada.api.impl.personalwebsite.PersonalWebsiteServiceImpl;
import me.pabloestrada.core.DatabaseConstants;
import me.pabloestrada.core.authentication.UserAuthenticator;
import me.pabloestrada.core.user.UserDAO;
import me.pabloestrada.core.personalwebsite.WebsiteInfoDAO;
import me.pabloestrada.credentials.CredentialsHelper;
import me.pabloestrada.database.PersianDatabase;
import me.pabloestrada.exercise.client.StravaClientModule;

public class PersonalWebsiteModule
    extends AbstractModule
{
    @Override
    protected void configure() {
        bind(AuthenticationService.class).to(AuthenticationServiceImpl.class);
        bind(PersonalWebsiteService.class).to(PersonalWebsiteServiceImpl.class);
        bind(ConnectionString.class).toInstance(new ConnectionString(DatabaseConstants.getMongoConnectionString()));
        bind(Gson.class).in(Singleton.class);
        bind(UserDAO.class).in(Singleton.class);
        bind(WebsiteInfoDAO.class).in(Singleton.class);
        bind(UserAuthenticator.class).in(Singleton.class);
        bind(CredentialsHelper.class).in(Singleton.class);

        // Exercise Tracker service
        install(new StravaClientModule());
        bind(PersianDatabase.class).in(Singleton.class);
        bind(ExerciseTrackerService.class).to(ExerciseTrackerServiceImpl.class);
    }
}
