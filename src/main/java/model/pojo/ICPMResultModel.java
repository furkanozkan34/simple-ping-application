package model.pojo;

import java.util.List;

public class ICPMResultModel {

    private String host;
    private List<String> icmpResults;

    public ICPMResultModel(String host, List<String> icmpResults) {
        this.host = host;
        this.icmpResults = icmpResults;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<String> getIcmpResults() {
        return icmpResults;
    }

    public void setIcmpResults(List<String> icmpResults) {
        this.icmpResults = icmpResults;
    }
}
