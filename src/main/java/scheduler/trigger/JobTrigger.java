package scheduler.trigger;

import model.enums.PingType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import properties.DynamicProperties;
import scheduler.job.PingJob;
import util.ApplicationUtil;

public class JobTrigger {

    private static final Logger log = LogManager.getLogger(JobTrigger.class);

    public void startAllJobs() {

        DynamicProperties dynamicProperties = ApplicationUtil.getInstance().getProperties();
        triggerForICMP(dynamicProperties.getFixedDelaySecondForICMP().intValue());
        triggerForTCP(dynamicProperties.getFixedDelaySecondForTCP().intValue());
        triggerForTraceRoute(dynamicProperties.getFixedDelaySecondForTraceRoute().intValue());
    }

    private void triggerForICMP(int fixedDelay) {

        try {
            JobDetail jobDetail = JobBuilder.newJob(PingJob.class).withIdentity("ICPMJob", "group1").build();
            jobDetail.getJobDataMap().put("PING_TYPE", PingType.ICMP);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("ICPMJob", "group1")
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(fixedDelay))
                    .build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("PingICMP job could'nt start because of this error :", e);
        }
    }

    private void triggerForTCP(int fixedDelay) {

        try {
            JobDetail jobDetail = JobBuilder.newJob(PingJob.class).withIdentity("TCPJob", "group2").build();
            jobDetail.getJobDataMap().put("PING_TYPE", PingType.TCP);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("TCPJob", "group2")
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(fixedDelay))
                    .build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("PingTCP job could'nt start because of this error :", e);
        }
    }

    private void triggerForTraceRoute(int fixedDelay) {

        try {
            JobDetail jobDetail = JobBuilder.newJob(PingJob.class).withIdentity("TraceRouteJob", "group3").build();
            jobDetail.getJobDataMap().put("PING_TYPE", PingType.TRACE_ROUTE);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("TraceRouteJob", "group3")
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(fixedDelay))
                    .build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("TraceRoute job could'nt start because of this error :", e);
        }
    }
}
