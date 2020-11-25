package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import properties.DynamicProperties;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PingWithICPMServiceTest {

    @Mock
    private DynamicProperties dynamicProperties;
    private PingWithICMPService pingWithICMPService;
    private String host = "google.com";

    @Before
    public void setUp() {
        pingWithICMPService = new PingWithICMPService(host, dynamicProperties);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCommandIsNull() {
        when(dynamicProperties.getPingCommand()).thenReturn(null);
        pingWithICMPService.getCommand(dynamicProperties, host);
        fail();
    }
}
