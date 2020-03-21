package me.pabloestrada.exercise.stravajob;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import me.pabloestrada.exercise.client.StravaClient;
import me.pabloestrada.exercise.core.ExerciseDAO;
import me.pabloestrada.exercise.core.exercise.StravaRun;
import me.pabloestrada.jobs.PersianJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public final class StravaRunTrackerJob
        extends PersianJob implements Job {

    private final StravaClient stravaClient;
    private final ExerciseDAO exerciseDAO;

    private static final String CURRENT_TIMEZONE = "America/New_York";

    @Inject
    public StravaRunTrackerJob(final StravaClient stravaClient, final ExerciseDAO exerciseDAO) {
        this.stravaClient = stravaClient;
        this.exerciseDAO = exerciseDAO;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        final List<StravaRun> runs =  stravaClient
                .getStravaRuns(exerciseDAO.hasStravaRuns()? getEpochOfStartOfCurrentDay() : 0)
                .orElse(new ArrayList<>());
        System.out.println("Inserting " + runs.size() + " Strava runs");
        System.out.println(runs);
        exerciseDAO.insertManyStravaRuns(runs);
    }

    private long getEpochOfStartOfCurrentDay() {
        final ZoneId zoneId = ZoneId.of(CURRENT_TIMEZONE);
        return LocalDate.now(zoneId).atStartOfDay(zoneId).toEpochSecond();
    }

    public static void main(String[] args) {
        final Injector schedulerInjector = Guice.createInjector(new TestTrackerModule());
    }
}
