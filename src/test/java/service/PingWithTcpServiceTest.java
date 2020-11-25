package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.WrongTypeOfReturnValue;
import org.mockito.runners.MockitoJUnitRunner;
import properties.DynamicProperties;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PingWithTcpServiceTest {

    @Mock
    private DynamicProperties dynamicProperties;
    private PingWithTCPService pingWithTCPService;

    @Before
    public void setUp() {
        pingWithTCPService = new PingWithTCPService("google.com", dynamicProperties);
    }

    @Test(expected = WrongTypeOfReturnValue.class)
    public void shouldThrowExceptionWhenTimeoutDurationIsNull() {
        when(dynamicProperties.getTimeoutDurationForTCP()).thenReturn(null);
        pingWithTCPService.run();
        fail();
    }
}
