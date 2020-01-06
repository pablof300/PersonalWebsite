package me.pabloestrada.exercise;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.jobs.PersianJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public final class ExerciseTrackerJob
        extends PersianJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    public static void main(String[] args) {
        Injector i = Guice.createInjector(new ExerciseTrackerModule());
        System.out.println(i.getInstance(StravaClient.class).getAuthorizationToken());
     }
}
