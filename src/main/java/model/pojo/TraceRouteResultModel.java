package model.pojo;

import java.util.List;

public class TraceRouteResultModel {

    private String host;
    private List<String> traceRouteResults;

    public TraceRouteResultModel() {
    }

    public TraceRouteResultModel(String host, List<String> traceRouteResults) {
        this.host = host;
        this.traceRouteResults = traceRouteResults;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<String> getTraceRouteResults() {
        return traceRouteResults;
    }

    public void setTraceRouteResults(List<String> traceRouteResults) {
        this.traceRouteResults = traceRouteResults;
    }
}
