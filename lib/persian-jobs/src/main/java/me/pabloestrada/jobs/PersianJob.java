package me.pabloestrada.jobs;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public class PersianJob {
    private Trigger trigger;
    private JobDetail jobDetail;

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }
}
