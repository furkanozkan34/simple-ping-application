package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.pojo.ICPMResultModel;
import model.pojo.TCPResultModel;
import model.pojo.TraceRouteResultModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ApplicationUtil;

import java.io.File;

class ResultStoreService {

    private static final ObjectMapper objectMapper = ApplicationUtil.getInstance().getObjectMapper();
    private static final File file = ApplicationUtil.getInstance().createStoreage();
    private static final Logger log = LogManager.getLogger(ResultStoreService.class);

    private ResultStoreService() {
    }

    static void storeICMPResult(ICPMResultModel icpmResultModel) {
        // store in somewhere
        try {
            String result = objectMapper.writeValueAsString(icpmResultModel.getIcmpResults());
            log.info("Ping-ICMP Result host :{}, result :{}", icpmResultModel.getHost(), result);
        } catch (Exception e) {
            log.error("Error occurred when store results for Ping-ICMP with error :", e);
        }
    }

    static void storeTCPResult(TCPResultModel tcpResultModel) {
        // store in somewhere
        try {
            String result = objectMapper.writeValueAsString(tcpResultModel);
            log.info("Ping-TCP Result host :{}, result :{}", tcpResultModel.getUrl(), result);
        } catch (Exception e) {
            log.error("Error occurred when store results for Ping-TCP with error :", e);
        }
    }

    static void storeTraceRouteResult(TraceRouteResultModel traceRouteResultModel) {
        // store in somewhere
        try {
            String result = objectMapper.writeValueAsString(traceRouteResultModel.getTraceRouteResults());
            log.info("TraceRoute  Result host :{}, result :{}", traceRouteResultModel.getHost(), result);
        } catch (Exception e) {
            log.error("Error occurred when store results for TraceRoute with error :", e);
        }
    }
}
