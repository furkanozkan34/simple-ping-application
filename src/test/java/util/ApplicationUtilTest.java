package util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import properties.DynamicProperties;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationUtilTest {

    @InjectMocks
    private ApplicationUtil applicationUtil;

    @Test
    public void shouldSuccessfullyReadProperties() {

        DynamicProperties expectedProperties = new DynamicProperties();
        expectedProperties.setHosts(Arrays.asList("bilyoner.com", "google.com"));
        expectedProperties.setGivenUrlToPostReport("localhost:880/report");

        DynamicProperties actualProperties = applicationUtil.getProperties();

        assertEquals(actualProperties.getHosts().get(0), expectedProperties.getHosts().get(0));
        assertEquals(actualProperties.getHosts().get(1), expectedProperties.getHosts().get(1));
        assertNotNull(actualProperties.getGivenUrlToPostReport());
        assertNull(expectedProperties.getFixedDelaySecondForICMP());
    }
}
