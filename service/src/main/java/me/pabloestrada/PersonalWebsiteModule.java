package me.pabloestrada;

import com.google.inject.AbstractModule;
import me.pabloestrada.api.ExerciseTrackerService;
import me.pabloestrada.api.impl.exercise.ExerciseTrackerServiceImpl;

public class PersonalWebsiteModule
    extends AbstractModule
{
    @Override
    protected void configure() {
        bind(ExerciseTrackerService.class).to(ExerciseTrackerServiceImpl.class);
    }
}
