package me.pabloestrada.scheduler;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.pabloestrada.credentials.CredentialsHelper;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.exercise.core.ExerciseDAO;
import me.pabloestrada.exercise.stravajob.StravaRunTrackerJob;
import me.pabloestrada.exercise.stravajob.StravaRunTrackerModule;
import net.halflite.guicequartzsample.scheduler.Quartz;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public final class SchedulerApplication {

    public static void main(final String[] args) {
        final Injector schedulerInjector = Guice.createInjector(new SchedulerModule());
        final Scheduler scheduler = schedulerInjector.getInstance(Quartz.class).getScheduler();

        try {
            scheduler.start();
            final JobDetail stravaRunTrackerJob = newJob(StravaRunTrackerJob.class)
                    .withIdentity("stravaRunTrackerJob", "exercise")
                    .build();

            // Trigger the job to run at what time and frequency
            final Trigger stravaRunTrackerJobTrigger = newTrigger()
                    .withIdentity("stravaRunTrackerTrigger", "exercise")
                    .startNow()
                    .withSchedule(simpleSchedule().withIntervalInMinutes(1).repeatForever())
                    .build();

            // tell scheduler to schedule the job using the trigger
            scheduler.scheduleJob(stravaRunTrackerJob, stravaRunTrackerJobTrigger);
        } catch (final SchedulerException e) {
            e.printStackTrace();
        }
    }
}