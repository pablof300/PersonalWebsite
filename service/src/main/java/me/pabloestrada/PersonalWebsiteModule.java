package me.pabloestrada;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import me.pabloestrada.api.AuthenticationService;
import me.pabloestrada.api.ExerciseTrackerService;
import me.pabloestrada.api.impl.authentication.AuthenticationServiceImpl;
import me.pabloestrada.api.impl.exercise.ExerciseTrackerServiceImpl;
import me.pabloestrada.core.authentication.UserAuthenticator;

public class PersonalWebsiteModule
    extends AbstractModule
{
    @Override
    protected void configure() {
        bind(ExerciseTrackerService.class).to(ExerciseTrackerServiceImpl.class);
        bind(AuthenticationService.class).to(AuthenticationServiceImpl.class);
        bind(UserAuthenticator.class).toInstance(new UserAuthenticator());
    }
}
