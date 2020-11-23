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

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public String getPingCommand() {
        return pingCommand;
    }

    public void setPingCommand(String pingCommand) {
        this.pingCommand = pingCommand;
    }

    public String getPingCommandPrefix() {
        return pingCommandPrefix;
    }

    public void setPingCommandPrefix(String pingCommandPrefix) {
        this.pingCommandPrefix = pingCommandPrefix;
    }

    public String getPingCommandCount() {
        return pingCommandCount;
    }

    public void setPingCommandCount(String pingCommandCount) {
        this.pingCommandCount = pingCommandCount;
    }

    public Long getFixedDelaySecondForICMP() {
        return fixedDelaySecondForICMP;
    }

    public void setFixedDelaySecondForICMP(Long fixedDelaySecondForICMP) {
        this.fixedDelaySecondForICMP = fixedDelaySecondForICMP;
    }

    public long getTimeoutDurationForTCP() {
        return timeoutDurationForTCP;
    }

    public void setTimeoutDurationForTCP(long timeoutDurationForTCP) {
        this.timeoutDurationForTCP = timeoutDurationForTCP;
    }

    public Long getFixedDelaySecondForTCP() {
        return fixedDelaySecondForTCP;
    }

    public void setFixedDelaySecondForTCP(Long fixedDelaySecondForTCP) {
        this.fixedDelaySecondForTCP = fixedDelaySecondForTCP;
    }

    public String getTraceRouteCommand() {
        return traceRouteCommand;
    }

    public void setTraceRouteCommand(String traceRouteCommand) {
        this.traceRouteCommand = traceRouteCommand;
    }

    public Long getFixedDelaySecondForTraceRoute() {
        return fixedDelaySecondForTraceRoute;
    }

    public void setFixedDelaySecondForTraceRoute(Long fixedDelaySecondForTraceRoute) {
        this.fixedDelaySecondForTraceRoute = fixedDelaySecondForTraceRoute;
    }

    public String getGivenUrlToPostReport() {
        return givenUrlToPostReport;
    }

    public void setGivenUrlToPostReport(String givenUrlToPostReport) {
        this.givenUrlToPostReport = givenUrlToPostReport;
    }
}
