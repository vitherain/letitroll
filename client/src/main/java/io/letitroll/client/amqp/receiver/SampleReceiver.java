package io.letitroll.client.amqp.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleReceiver.class);

    public void receive(final String message) {
        LOGGER.info("Received message={}", message);
    }
}
