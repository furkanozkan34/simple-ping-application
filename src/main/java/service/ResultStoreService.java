package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.constant.Constant;
import model.enums.PingType;
import model.pojo.ICPMResultModel;
import model.pojo.TCPResultModel;
import model.pojo.TraceRouteResultModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ApplicationUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class ResultStoreService {

    private static final Logger log = LogManager.getLogger(ResultStoreService.class);
    private static final ObjectMapper objectMapper = ApplicationUtil.getInstance().getObjectMapper();

    private ResultStoreService() {
    }

    static void storeICMPResult(ICPMResultModel icpmResultModel) {
        try {
            String result = objectMapper.writeValueAsString(icpmResultModel.getIcmpResults());
            appendToFile(Constant.ICPM_FILE_NAME, PingType.ICMP, icpmResultModel.getHost(), icpmResultModel.getIcmpResults());
            log.info("Ping-ICMP Result host :{}, result :{}", icpmResultModel.getHost(), result);
        } catch (Exception e) {
            log.error("Error occurred when store results for Ping-ICMP, error:", e);
        }
    }

    static void storeTCPResult(TCPResultModel tcpResultModel) {
        try {
            String result = objectMapper.writeValueAsString(tcpResultModel);
            appendToFile(Constant.TCP_FILE_NAME, PingType.TCP, tcpResultModel.getUrl(), Collections.singletonList(Constant.RESPONSE_STATUS.concat(String.valueOf(tcpResultModel.getHttpStatus()).concat(Constant.SEPARATOR).concat(Constant.RESPONSE_TIME).concat(String.valueOf(tcpResultModel.getResponseTime())))));
            log.info("Ping-TCP Result host :{}, result :{}", tcpResultModel.getUrl(), result);
        } catch (Exception e) {
            log.error("Error occurred when store results for Ping-TCP, error:", e);
        }
    }

    static void storeTraceRouteResult(TraceRouteResultModel traceRouteResultModel) {
        try {
            String result = objectMapper.writeValueAsString(traceRouteResultModel.getTraceRouteResults());
            appendToFile(Constant.TRACE_ROUTE_FILE_NAME, PingType.TRACE_ROUTE, traceRouteResultModel.getHost(), traceRouteResultModel.getTraceRouteResults());
            log.info("TraceRoute  Result host :{}, result :{}", traceRouteResultModel.getHost(), result);
        } catch (Exception e) {
            log.error("Error occurred when store results for TraceRoute, error:", e);
        }
    }


    private static void appendToFile(String fileName, PingType pingType, String host, List<String> results) {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file.getName(), true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            results
                    .stream()
                    .filter(k -> Objects.nonNull(k) && !k.equals(""))
                    .map(t -> apply(pingType, host, t))
                    .forEach(row -> write(bw, row));
            bw.write(Constant.END_LINE);
            bw.close();
        } catch (IOException e) {
            log.error("Error occurred when creating file , error: ", e);
        }
    }

    private static String apply(PingType pingType, String host, String result) {
        return String.format(Constant.FORMAT, pingType.name(), Constant.MULTI_SEPARATOR, host, Constant.MULTI_SEPARATOR, result, Constant.END_LINE);
    }

    private static void write(BufferedWriter bw, String row) {
        try {
            bw.write(row);
        } catch (IOException e) {
            log.error("Error occurred when writing row to file, error:", e);
        }
    }
}
