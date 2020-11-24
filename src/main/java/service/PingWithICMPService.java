package service;

import model.enums.PingType;
import model.pojo.ICPMResultModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties.DynamicProperties;
import util.ApplicationUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PingWithICMPService implements IPinger {

    private final String host;
    private static final Logger log = LogManager.getLogger(PingWithICMPService.class);

    public PingWithICMPService(String host) {
        this.host = host;
    }

    @Override
    public void run() {

        try {
            DynamicProperties dynamicProperties = ApplicationUtil.getInstance().getProperties();
            var command = getCommand(dynamicProperties, host);
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
            List<String> results = inputStream.lines().filter(Objects::nonNull).collect(Collectors.toList());

            if (results.isEmpty()) {
                BufferedReader errorStream = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                List<String> errors = errorStream.lines().filter(Objects::nonNull).collect(Collectors.toList());
                if (!errors.isEmpty()) {
                    var errorMessage = String.join(",", errors);
                    log.error("Error occurred when processing Ping-ICMP for this host :{}, with errorMessage :{}", host, errorMessage);
                    ReportService.report(host, PingType.ICMP, errorMessage);
                    return;
                }
            }

            ICPMResultModel icpmResultModel = new ICPMResultModel(host, results);
            ResultStoreService.storeICMPResult(icpmResultModel);
        } catch (Exception e) {
            log.error("Error occurred when processing Ping-ICMP for this host :{}, with error", host, e);
            ReportService.report(host, PingType.ICMP, e.getMessage());
        }
    }

    private String getCommand(DynamicProperties dynamicProperties, String host) {
        return dynamicProperties.getPingCommand()
                .concat(" ")
                .concat(dynamicProperties.getPingCommandPrefix())
                .concat(" ")
                .concat(dynamicProperties.getPingCommandCount())
                .concat(" ")
                .concat(host);
    }
}
