package io.letitroll.be;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

public class BeApplicationContextTest extends BaseSpringTest {

    /**
     * Temporary
     */
    @TestConfiguration
    static class Config {

        @Bean
        public RabbitTemplate rabbitTemplate() {
            return Mockito.mock(RabbitTemplate.class);
        }
    }

    @Test
    public void contextLoads() {
    }
}
