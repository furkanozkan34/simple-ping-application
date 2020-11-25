package scheduler;

import model.constant.Constant;
import model.enums.PingType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import scheduler.job.PingJob;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PingJobTest {

    @InjectMocks
    private PingJob pingJob;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenJobDataMapKeyUndefined() {

        JobDetail jobDetail = JobBuilder.newJob(PingJob.class).withIdentity(Constant.ICMP_JOB_NAME, Constant.ICMP_JOB_GROUP).build();
        jobDetail.getJobDataMap().put(Constant.JOB_DATA_MAP_KEY, "UNDEFINED");

        JobExecutionContext jobExecutionContext = mock(JobExecutionContext.class);
        when(jobExecutionContext.getJobDetail()).thenReturn(jobDetail);
        pingJob.execute(jobExecutionContext);
        fail();
    }

    @Test
    public void shouldSuccessfullyExecuteJob() {

        JobDetail jobDetail = JobBuilder.newJob(PingJob.class).withIdentity(Constant.ICMP_JOB_NAME, Constant.ICMP_JOB_GROUP).build();
        jobDetail.getJobDataMap().put(Constant.JOB_DATA_MAP_KEY, PingType.TCP);
        JobExecutionContext jobExecutionContext = mock(JobExecutionContext.class);
        when(jobExecutionContext.getJobDetail()).thenReturn(jobDetail);

        pingJob.execute(jobExecutionContext);
    }
}
