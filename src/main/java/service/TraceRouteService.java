package service;

import model.pojo.ReportModel;
import model.pojo.TraceRouteResultModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ApplicationUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TraceRouteService implements IPinger {

    private final String host;
    private static final Logger log = LogManager.getLogger(TraceRouteService.class);

    public TraceRouteService(String host) {
        this.host = host;
    }

    @Override
    public void run() {

        try {
            var command = ApplicationUtil.getInstance().getProperties().getTraceRouteCommand().concat(" ").concat(host);
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
            List<String> results = inputStream.lines().filter(Objects::nonNull).collect(Collectors.toList());

            TraceRouteResultModel traceRouteResultModel = new TraceRouteResultModel(host, results);
            ResultStoreService.storeTraceRouteResult(traceRouteResultModel);
        } catch (Exception e) {
            log.error("Error occurred when processing TraceRoute for this host :{}, with error", host, e);
            ReportModel reportModel = new ReportModel();
            reportModel.setHost(host);
            reportModel.setTrace(e.getMessage());
            ReportService.postDataToGivenUrl(reportModel);
        }
    }
}
