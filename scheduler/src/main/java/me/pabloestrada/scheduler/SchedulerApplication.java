package me.pabloestrada.scheduler;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.halflite.guicequartzsample.scheduler.Quartz;
import org.quartz.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public final class SchedulerApplication {

    public static void main(final String[] args) {
        final Injector schedulerInjector = Guice.createInjector(new SchedulerModule());
        final Scheduler scheduler = schedulerInjector.getInstance(Quartz.class).getScheduler();

        try {
            scheduler.start();
//            JobDetail jobDetail = newJob(Cat.class)
//                    .withIdentity("job1", "group1")
//                    .build();
//
//            // Trigger the job to run at what time and frequency
//            Trigger trigger = newTrigger()
//                    .withIdentity("trigger1", "group 1")
//                    .startNow()
//                    .withSchedule(simpleSchedule().withIntervalInMilliseconds(1000L).repeatForever())
//                    .build();
//
//            // tell scheduler to schedule the job using the trigger
//            scheduler.scheduleJob(jobDetail, trigger);

        } catch (final SchedulerException e) {
            e.printStackTrace();
        }
    }
}
