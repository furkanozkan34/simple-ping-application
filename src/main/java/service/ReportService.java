package service;

import model.enums.PingType;
import model.pojo.ReportModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ReportService {

    private static final Logger log = LogManager.getLogger(ReportService.class);

    private ReportService() {
    }

    private static void postDataToGivenUrl(ReportModel reportModel) {

        try {
            /*String uri = ApplicationUtil.getInstance().getProperties().getGivenUrlToPostReport();
            String requestBody = new ObjectMapper().writeValueAsString(reportModel);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Report Service Result :{}", response.body());*/
        } catch (Exception e) {
            log.error("Error occurred when call report service for this host :{} with error :", reportModel.getHost(), e);
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
            default:
                reportModel.setTrace(errorMessage);
        }
        return reportModel;
    }
}
