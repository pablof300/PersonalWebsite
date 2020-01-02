package me.pabloestrada.exercise;

import me.pabloestrada.jobs.PersianJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public final class ExerciseTrackerJob
        extends PersianJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
