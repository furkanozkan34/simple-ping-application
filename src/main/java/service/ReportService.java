package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.constant.Constant;
import model.enums.PingType;
import model.pojo.ReportModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ApplicationUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class ReportService {

    private static final Logger log = LogManager.getLogger(ReportService.class);
    private static final ObjectMapper objectMapper = ApplicationUtil.getInstance().getObjectMapper();

    private ReportService() {
    }

    private static void postDataToGivenUrl(ReportModel reportModel) {

        try {
            String uri = ApplicationUtil.getInstance().getProperties().getGivenUrlToPostReport();
            String requestBody = objectMapper.writeValueAsString(reportModel);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header(Constant.CONTENT_TYPE, Constant.APPLICATION_JSON)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Report Service Result :{}", response.body());
        } catch (Exception e) {
            log.error("Error occurred when call report service for this host :{}, error :", reportModel.getHost(), e);
        }
    }

    static void report(String host, PingType pingType, String errorMessage) {
        ReportModel reportModel = reportBuilder(host, pingType, errorMessage);
        postDataToGivenUrl(reportModel);
    }

    private static ReportModel reportBuilder(String host, PingType pingType, String errorMessage) {
        ReportModel reportModel = new ReportModel();
        reportModel.setHost(host);
        switch (pingType) {
            case ICMP:
                reportModel.setIcpmPing(errorMessage);
                break;
            case TCP:
                reportModel.setTcpPing(errorMessage);
                break;
            case TRACE_ROUTE:
                reportModel.setTrace(errorMessage);
                break;
        }
        return reportModel;
    }
}
