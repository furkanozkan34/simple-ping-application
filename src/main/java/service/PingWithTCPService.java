package service;

import model.constant.Constant;
import model.enums.PingType;
import model.pojo.TCPResultModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties.DynamicProperties;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PingWithTCPService implements IPinger {

    private final String host;
    private final DynamicProperties dynamicProperties;
    private static final Logger log = LogManager.getLogger(PingWithTCPService.class);

    public PingWithTCPService(String host, DynamicProperties dynamicProperties) {
        this.host = host;
        this.dynamicProperties = dynamicProperties;
    }

    @Override
    public void run() {

        try {
            HttpClient client = HttpClient.newHttpClient();
            var timeoutDuration = dynamicProperties.getTimeoutDurationForTCP();
            var uri = Constant.HTTPS.concat(host);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .timeout(Duration.ofSeconds(Long.valueOf(timeoutDuration)))
                    .build();
            var startTime = System.nanoTime();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var responseTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);

            TCPResultModel tcpResultModel = new TCPResultModel(uri, responseTime, response.statusCode());
            ResultStoreService.storeTCPResult(tcpResultModel);
        } catch (Exception e) {
            log.error("Error occurred when processing Ping-TCP for this host :{}, error", host, e);
            ReportService.report(host, PingType.TCP, e.getMessage());
        }
    }
}
