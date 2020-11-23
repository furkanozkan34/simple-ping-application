package model.pojo;

public class TCPResultModel {

    private String url;
    private long responseTime;
    private int httpStatus;

    public TCPResultModel() {
    }

    public TCPResultModel(String url, long responseTime, int httpStatus) {
        this.url = url;
        this.responseTime = responseTime;
        this.httpStatus = httpStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}

