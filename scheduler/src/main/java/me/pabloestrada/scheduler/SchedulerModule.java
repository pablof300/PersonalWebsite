package me.pabloestrada.scheduler;

import com.google.inject.AbstractModule;
import me.pabloestrada.exercise.stravajob.StravaRunTrackerModule;
import net.halflite.guicequartzsample.config.ConfigModule;

final class SchedulerModule
        extends AbstractModule
{
    @Override
    protected void configure() {
        install(new ConfigModule());
        install(new StravaRunTrackerModule());
    }
}