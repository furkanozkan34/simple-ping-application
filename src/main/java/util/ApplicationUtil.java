package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import properties.DynamicProperties;

import java.io.IOException;
import java.io.InputStream;

public class ApplicationUtil {

    private static final Logger log = LogManager.getLogger(ApplicationUtil.class);

    private static ApplicationUtil instance = null;

    public static ApplicationUtil getInstance() {
        if (instance == null) {
            instance = new ApplicationUtil();
            instance.getProperties();
            instance.getObjectMapper();
        }
        return instance;
    }

    public DynamicProperties getProperties() {

        Yaml yaml = new Yaml();
        try (InputStream in = ApplicationUtil.class.getResourceAsStream("/application.yaml")) {
            return yaml.loadAs(in, DynamicProperties.class);
        } catch (IOException e) {
            log.error("Could'nt read yml because of error :", e);
            throw new IllegalStateException("could'nt read yaml..");
        }
    }

    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
