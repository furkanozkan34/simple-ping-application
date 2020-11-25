package service;

import model.constant.Constant;
import model.enums.PingType;
import model.pojo.ICPMResultModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties.DynamicProperties;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PingWithICMPService implements IPinger {

    private final String host;
    private final DynamicProperties dynamicProperties;
    private static final Logger log = LogManager.getLogger(PingWithICMPService.class);

    public PingWithICMPService(String host, DynamicProperties dynamicProperties) {
        this.host = host;
        this.dynamicProperties = dynamicProperties;
    }

    @Override
    public void run() {

        try {
            var command = getCommand(dynamicProperties, host);
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
            List<String> results = inputStream.lines().filter(Objects::nonNull).collect(Collectors.toList());

            if (results.isEmpty()) {
                BufferedReader errorStream = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                List<String> errors = errorStream.lines().filter(Objects::nonNull).collect(Collectors.toList());
                if (!errors.isEmpty()) {
                    var errorMessage = String.join(",", errors);
                    log.error("Error occurred when processing Ping-ICMP  host :{}, errorMessage :{}", host, errorMessage);
                    ReportService.report(host, PingType.ICMP, errorMessage);
                    return;
                }
            }

            ICPMResultModel icpmResultModel = new ICPMResultModel(host, results);
            ResultStoreService.storeICMPResult(icpmResultModel);
        } catch (Exception e) {
            log.error("Error occurred when processing Ping-ICMP for this host :{}, error:", host, e);
            ReportService.report(host, PingType.ICMP, e.getMessage());
        }
    }

     String getCommand(DynamicProperties dynamicProperties, String host) {
        return dynamicProperties.getPingCommand()
                .concat(Constant.BLANK)
                .concat(dynamicProperties.getPingCommandPrefix())
                .concat(Constant.BLANK)
                .concat(dynamicProperties.getPingCommandCount())
                .concat(Constant.BLANK)
                .concat(host);
    }
}
