package properties;

import java.util.List;

public class DynamicProperties {

    private List<String> hosts;
    private String pingCommand;
    private String pingCommandPrefix;
    private String pingCommandCount;
    private String fixedDelaySecondForICMP;

    private String timeoutDurationForTCP;
    private String fixedDelaySecondForTCP;

    private String traceRouteCommand;
    private String fixedDelaySecondForTraceRoute;
    private String givenUrlToPostReport;

    public DynamicProperties() {
    }

    public DynamicProperties(List<String> hosts, String pingCommand, String pingCommandPrefix, String pingCommandCount, String fixedDelaySecondForICMP, String timeoutDurationForTCP, String fixedDelaySecondForTCP, String traceRouteCommand, String fixedDelaySecondForTraceRoute, String givenUrlToPostReport) {
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

    public String getFixedDelaySecondForICMP() {
        return fixedDelaySecondForICMP;
    }

    public void setFixedDelaySecondForICMP(String fixedDelaySecondForICMP) {
        this.fixedDelaySecondForICMP = fixedDelaySecondForICMP;
    }

    public String getTimeoutDurationForTCP() {
        return timeoutDurationForTCP;
    }

    public void setTimeoutDurationForTCP(String timeoutDurationForTCP) {
        this.timeoutDurationForTCP = timeoutDurationForTCP;
    }

    public String getFixedDelaySecondForTCP() {
        return fixedDelaySecondForTCP;
    }

    public void setFixedDelaySecondForTCP(String fixedDelaySecondForTCP) {
        this.fixedDelaySecondForTCP = fixedDelaySecondForTCP;
    }

    public String getTraceRouteCommand() {
        return traceRouteCommand;
    }

    public void setTraceRouteCommand(String traceRouteCommand) {
        this.traceRouteCommand = traceRouteCommand;
    }

    public String getFixedDelaySecondForTraceRoute() {
        return fixedDelaySecondForTraceRoute;
    }

    public void setFixedDelaySecondForTraceRoute(String fixedDelaySecondForTraceRoute) {
        this.fixedDelaySecondForTraceRoute = fixedDelaySecondForTraceRoute;
    }

    public String getGivenUrlToPostReport() {
        return givenUrlToPostReport;
    }

    public void setGivenUrlToPostReport(String givenUrlToPostReport) {
        this.givenUrlToPostReport = givenUrlToPostReport;
    }
}
