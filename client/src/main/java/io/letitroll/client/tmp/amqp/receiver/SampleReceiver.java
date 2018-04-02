package io.letitroll.client.tmp.amqp.receiver;

import io.letitroll.common.feature.domain.Feature;
import io.letitroll.common.feature.dto.FeatureDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleReceiver.class);

    public void receive(final FeatureDto message) {
        LOGGER.info("Received message={}", message);
    }
}
