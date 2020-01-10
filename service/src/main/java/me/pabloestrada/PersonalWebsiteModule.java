package me.pabloestrada;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import me.pabloestrada.api.AuthenticationService;
import me.pabloestrada.api.ExerciseTrackerService;
import me.pabloestrada.api.WebsiteGeneratorService;
import me.pabloestrada.api.impl.authentication.AuthenticationServiceImpl;
import me.pabloestrada.api.impl.exercise.ExerciseTrackerServiceImpl;
import me.pabloestrada.api.impl.websitegenerator.WebsiteGeneratorServiceImpl;
import me.pabloestrada.core.authentication.UserAuthenticator;
import me.pabloestrada.core.user.UserDAO;
import me.pabloestrada.credentials.CredentialsHelper;

public class PersonalWebsiteModule
    extends AbstractModule
{
    @Override
    protected void configure() {
        bind(ExerciseTrackerService.class).to(ExerciseTrackerServiceImpl.class);
        bind(AuthenticationService.class).to(AuthenticationServiceImpl.class);
        bind(WebsiteGeneratorService.class).to(WebsiteGeneratorServiceImpl.class);
        bind(UserDAO.class).in(Singleton.class);
        bind(UserAuthenticator.class).in(Singleton.class);
        bind(CredentialsHelper.class).in(Singleton.class);
    }
}
