package service;

import model.pojo.ICPMResultModel;
import model.pojo.ReportModel;
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

            ICPMResultModel icpmResultModel = new ICPMResultModel(host, results);
            ResultStoreService.storeICMPResult(icpmResultModel);
        } catch (Exception e) {
            log.error("Error occurred when processing Ping-ICMP for this host :{}, with error", host, e);
            ReportModel reportModel = new ReportModel();
            reportModel.setHost(host);
            reportModel.setIcpmPing(e.getMessage());
            ReportService.postDataToGivenUrl(reportModel);
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
