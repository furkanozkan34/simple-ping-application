package thread;

import model.enums.PingType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import properties.DynamicProperties;
import service.IPinger;
import service.PingWithICMPService;
import util.ApplicationUtil;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PingExecutorTest {

    @InjectMocks
    private PingExecutor pingExecutor;

    @Mock
    private ApplicationUtil applicationUtil;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPingTypeUndefined() {
        DynamicProperties properties = new DynamicProperties();
        properties.setHosts(Arrays.asList("google.com", "facebook.com"));
        when(applicationUtil.getProperties()).thenReturn(properties);
        pingExecutor.run(PingType.UN_DEFINED);
        fail();
    }

    @Test
    public void shouldSuccessfullyCreateCorrectService() {

        DynamicProperties dynamicProperties = new DynamicProperties();
        PingWithICMPService expectedService = new PingWithICMPService("google.com", dynamicProperties);
        IPinger pinger = pingExecutor.getService(PingType.ICMP, "google.com");
        assertEquals(pinger.getClass(), expectedService.getClass());
    }
}
