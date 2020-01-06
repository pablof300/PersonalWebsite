package me.pabloestrada.exercise;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.jobs.PersianJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public final class ExerciseTrackerJob
        extends PersianJob implements Job {

    private final StravaClient stravaClient;

    @Inject
    public ExerciseTrackerJob(final StravaClient stravaClient) {
        this.stravaClient = stravaClient;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        stravaClient.getAllSummaryActivities().ifPresent(activities -> {
            activities.forEach(a -> System.out.println(a.getStartLatitudeAndLongitude()));
        });
    }

    public static void main(String[] args) {
        Injector i = Guice.createInjector(new ExerciseTrackerModule());
        i.getInstance(StravaClient.class).getAllSummaryActivities().ifPresent(activities -> {
            System.out.println(activities);
            activities.forEach(a -> System.out.println(a.getStartLatitudeAndLongitude()));
        });
     }
}
