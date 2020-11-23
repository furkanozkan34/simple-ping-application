import scheduler.trigger.JobTrigger;

public class SimplePingApplication {

    public static void main(String[] args) {
        JobTrigger jobTrigger = new JobTrigger();
        jobTrigger.startAllJobs();
    }
}
