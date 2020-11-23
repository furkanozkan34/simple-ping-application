package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import properties.DynamicProperties;

import java.io.File;

public class ApplicationUtil {

    private static final Logger logger = LogManager.getLogger(ApplicationUtil.class);

    private static ApplicationUtil instance = null;

    public static ApplicationUtil getInstance() {
        if (instance == null) {
            instance = new ApplicationUtil();
            instance.getProperties();
        }
        return instance;
    }

    public DynamicProperties getProperties() {

        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();
            return mapper.readValue(new File("src/main/resources/application.yaml"), DynamicProperties.class);
        } catch (Exception e) {
            logger.error("Error occurred when read properties from yml with error:", e);
            throw new IllegalStateException("could'nt read yaml..");
        }
    }
}
