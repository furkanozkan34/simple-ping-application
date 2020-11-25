package scheduler.job;

import model.constant.Constant;
import model.enums.PingType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import thread.PingExecutor;

public class PingJob implements Job {

    private static final Logger log = LogManager.getLogger(PingJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        try {
            JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            var pingType = (PingType) dataMap.get(Constant.JOB_DATA_MAP_KEY);
            PingExecutor pingExecutor = new PingExecutor();
            pingExecutor.run(pingType);
        } catch (Exception e) {
            log.error("Undefined PingType, job couldn't start because of this error:", e);
            throw new IllegalArgumentException("Undefined PingType");
        }
    }
}
