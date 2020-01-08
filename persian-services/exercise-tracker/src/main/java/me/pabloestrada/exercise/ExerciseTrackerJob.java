package me.pabloestrada.exercise;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.exercise.core.ExerciseDAO;
import me.pabloestrada.exercise.core.exercise.Exercise;
import me.pabloestrada.exercise.core.exercise.ExerciseSummary;
import me.pabloestrada.exercise.core.exercise.StravaRun;
import me.pabloestrada.exercise.onboarder.OnboarderApplication;
import me.pabloestrada.jobs.PersianJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.List;

public final class ExerciseTrackerJob
        extends PersianJob implements Job {

    private final StravaClient stravaClient;
    private final ExerciseDAO exerciseDAO;
    private final OnboarderApplication onboarderApplication;

    @Inject
    public ExerciseTrackerJob(final StravaClient stravaClient, final ExerciseDAO exerciseDAO, final OnboarderApplication onboarderApplication) {
        this.stravaClient = stravaClient;
        this.exerciseDAO = exerciseDAO;
        this.onboarderApplication = onboarderApplication;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (exerciseDAO.isExerciseSummaryCollectionEmpty()) {
            onboarderApplication.execute();
        }

        exerciseDAO.getExerciseSummary().ifPresent(exerciseSummary -> {
            final List<StravaRun> runs =  stravaClient.getSummaryActivities(
                    exerciseSummary.getLastTimeOfSummaryUpdate().getTime() / 1000L + 1L).orElse(new ArrayList<>());


        });

        // TODO
        // Error logging of why there is no exercise summary

    }

    public static void main(String[] args) throws JobExecutionException {
        Injector i = Guice.createInjector(new ExerciseTrackerModule());
        i.getInstance(ExerciseTrackerJob.class).execute(null);
     }
}
