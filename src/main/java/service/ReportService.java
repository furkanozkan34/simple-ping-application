package service;

import model.pojo.ReportModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportService {

    private static final Logger log = LogManager.getLogger(ReportService.class);

    private ReportService() {
    }

    static void postDataToGivenUrl(ReportModel reportModel) {

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
}
