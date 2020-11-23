package model.pojo;

public class ReportModel {

    private String host;
    private String icpmPing;
    private String tcpPing;
    private String trace;

    public ReportModel() {
    }

    public ReportModel(String host, String icpmPing, String tcpPing, String trace) {
        this.host = host;
        this.icpmPing = icpmPing;
        this.tcpPing = tcpPing;
        this.trace = trace;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIcpmPing() {
        return icpmPing;
    }

    public void setIcpmPing(String icpmPing) {
        this.icpmPing = icpmPing;
    }

    public String getTcpPing() {
        return tcpPing;
    }

    public void setTcpPing(String tcpPing) {
        this.tcpPing = tcpPing;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }


}
