package thread;

import model.enums.PingType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IPinger;
import service.PingWithICMPService;
import service.PingWithTCPService;
import service.TraceRouteService;
import util.ApplicationUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PingExecutor {

    private static final Logger log = LogManager.getLogger(PingExecutor.class);

    public void run(PingType pingType) {
        var hostList = ApplicationUtil.getInstance().getProperties().getHosts();
        ExecutorService executor = Executors.newFixedThreadPool(hostList.size());
        hostList.forEach(t -> {
            Runnable worker = getService(pingType, t);
            executor.execute(worker);
        });
        executor.shutdown();
    }

    private IPinger getService(PingType pingType, String host) {
        switch (pingType) {
            case ICMP:
                return new PingWithICMPService(host);
            case TCP:
                return new PingWithTCPService(host);
            case TRACE_ROUTE:
                return new TraceRouteService(host);
            default:
                log.error("Invalid value of PingType, proper service could'nt create. ");
                throw new IllegalArgumentException("Invalid value of PingType.");
        }
    }
}
