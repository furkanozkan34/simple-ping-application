package properties;

import java.util.List;

public class DynamicProperties {

    private List<String> hosts;
    private String pingCommand;
    private String pingCommandPrefix;
    private String pingCommandCount;
    private Long fixedDelaySecondForICMP;

    private long timeoutDurationForTCP;
    private Long fixedDelaySecondForTCP;

    private String traceRouteCommand;
    private Long fixedDelaySecondForTraceRoute;
    private String givenUrlToPostReport;

    public DynamicProperties() {
    }

    public DynamicProperties(List<String> hosts, String pingCommand, String pingCommandPrefix, String pingCommandCount, Long fixedDelaySecondForICMP, long timeoutDurationForTCP, Long fixedDelaySecondForTCP, String traceRouteCommand, Long fixedDelaySecondForTraceRoute, String givenUrlToPostReport) {
        this.hosts = hosts;
        this.pingCommand = pingCommand;
        this.pingCommandPrefix = pingCommandPrefix;
        this.pingCommandCount = pingCommandCount;
        this.fixedDelaySecondForICMP = fixedDelaySecondForICMP;
        this.timeoutDurationForTCP = timeoutDurationForTCP;
        this.fixedDelaySecondForTCP = fixedDelaySecondForTCP;
        this.traceRouteCommand = traceRouteCommand;
        this.fixedDelaySecondForTraceRoute = fixedDelaySecondForTraceRoute;
        this.givenUrlToPostReport = givenUrlToPostReport;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public String getPingCommand() {
        return pingCommand;
    }

    public String getPingCommandPrefix() {
        return pingCommandPrefix;
    }

    public String getPingCommandCount() {
        return pingCommandCount;
    }

    public Long getFixedDelaySecondForICMP() {
        return fixedDelaySecondForICMP;
    }

    public long getTimeoutDurationForTCP() {
        return timeoutDurationForTCP;
    }

    public Long getFixedDelaySecondForTCP() {
        return fixedDelaySecondForTCP;
    }

    public String getTraceRouteCommand() {
        return traceRouteCommand;
    }

    public Long getFixedDelaySecondForTraceRoute() {
        return fixedDelaySecondForTraceRoute;
    }

    public String getGivenUrlToPostReport() {
        return givenUrlToPostReport;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public void setGivenUrlToPostReport(String givenUrlToPostReport) {
        this.givenUrlToPostReport = givenUrlToPostReport;
    }

    public void setPingCommand(String pingCommand) {
        this.pingCommand = pingCommand;
    }

    public void setPingCommandPrefix(String pingCommandPrefix) {
        this.pingCommandPrefix = pingCommandPrefix;
    }

    public void setPingCommandCount(String pingCommandCount) {
        this.pingCommandCount = pingCommandCount;
    }
}
