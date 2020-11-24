package model.constant;

public class Constant {

    private Constant() {
        throw new UnsupportedOperationException("Unsupported operation!");
    }

    public static final String BLANK = " ";
    public static final String END_LINE = "\n";
    public static final String SEPARATOR = "\t";
    public static final String MULTI_SEPARATOR = "\t\t\t";
    public static final String FORMAT= "%s%s%s%s%s%s";
    public static final String ICPM_FILE_NAME = "icmp_result.txt";
    public static final String TCP_FILE_NAME = "tcp_result.txt";
    public static final String TRACE_ROUTE_FILE_NAME = "trace_route_result.txt";
    public static final String RESPONSE_STATUS = "HTTP Response Status:";
    public static final String RESPONSE_TIME = "Response Time (ms): ";
    public static final String HTTPS = "https://www.";
    public static final String CONTENT_TYPE= "Content-Type";
    public static final String APPLICATION_JSON= "application/json";

    public static final String JOB_DATA_MAP_KEY = "PING_TYPE";
    public static final String ICMP_JOB_NAME = "ICPMJob";
    public static final String ICMP_JOB_GROUP= "group1";

    public static final String TCP_JOB_NAME = "TCPJob";
    public static final String TCP_JOB_GROUP= "group2";

    public static final String TRACE_ROUTE_JOB_NAME = "TraceRouteJob";
    public static final String TRACE_ROUTE_GROUP= "group3";
}
