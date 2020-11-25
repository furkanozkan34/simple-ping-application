package scheduler.trigger;

import model.constant.Constant;
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
        triggerForICMP(Integer.valueOf(dynamicProperties.getFixedDelaySecondForICMP()));
        triggerForTCP(Integer.valueOf(dynamicProperties.getFixedDelaySecondForTCP()));
        triggerForTraceRoute(Integer.valueOf(dynamicProperties.getFixedDelaySecondForTraceRoute()));
    }

    private void triggerForICMP(int fixedDelay) {

        try {
            JobDetail jobDetail = JobBuilder.newJob(PingJob.class).withIdentity(Constant.ICMP_JOB_NAME, Constant.ICMP_JOB_GROUP).build();
            jobDetail.getJobDataMap().put(Constant.JOB_DATA_MAP_KEY, PingType.ICMP);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(Constant.ICMP_JOB_NAME, Constant.ICMP_JOB_GROUP)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(fixedDelay))
                    .build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("PingICMP job could'nt start because of this error:", e);
        }
    }

    private void triggerForTCP(int fixedDelay) {

        try {
            JobDetail jobDetail = JobBuilder.newJob(PingJob.class).withIdentity(Constant.TCP_JOB_NAME, Constant.TCP_JOB_GROUP).build();
            jobDetail.getJobDataMap().put(Constant.JOB_DATA_MAP_KEY, PingType.TCP);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(Constant.TCP_JOB_NAME, Constant.TCP_JOB_GROUP)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(fixedDelay))
                    .build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("PingTCP job could'nt start because of this error:", e);
        }
    }

    private void triggerForTraceRoute(int fixedDelay) {

        try {
            JobDetail jobDetail = JobBuilder.newJob(PingJob.class).withIdentity(Constant.TRACE_ROUTE_JOB_NAME, Constant.TRACE_ROUTE_GROUP).build();
            jobDetail.getJobDataMap().put(Constant.JOB_DATA_MAP_KEY, PingType.TRACE_ROUTE);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(Constant.TRACE_ROUTE_JOB_NAME, Constant.TRACE_ROUTE_GROUP)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(fixedDelay))
                    .build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("TraceRoute job could'nt start because of this error:", e);
        }
    }
}
