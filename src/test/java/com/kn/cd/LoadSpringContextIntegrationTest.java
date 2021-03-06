package com.kn.cd;

import com.kn.cd.service.CoindeskCLIRunner;
import com.kn.cd.service.bpi.BPIService;
import com.kn.cd.service.cd.CoindeskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CoindeskDemoApplication.class},
        initializers = ConfigDataApplicationContextInitializer.class)
public class LoadSpringContextIntegrationTest {

    @SpyBean
    CoindeskService coindeskService;

    @SpyBean
    BPIService bpiService;

    @SpyBean
    CoindeskCLIRunner coindeskCLIRunner;

    @Test
    void whenContextLoads_thenRunnersDoNotRun() {
        assertNotNull(coindeskService);
        assertNotNull(bpiService);
        assertNotNull(coindeskCLIRunner);

        verify(bpiService, times(0)).getCurrentBPI(any());
        verify(bpiService, times(0)).getHistoricalBPI(any(), any(), any());
        verify(coindeskService, times(0)).run(any());
        verify(coindeskCLIRunner, times(0)).run(any());
    }

}
