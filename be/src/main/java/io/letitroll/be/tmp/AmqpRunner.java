package io.letitroll.be.tmp;

import io.letitroll.common.feature.domain.FeatureType;
import io.letitroll.common.feature.dto.FeatureDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptySet;

@Component
public class AmqpRunner implements CommandLineRunner {

    private static final String TOPIC_EXCHANGE_NAME = "spring-boot-exchange";

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public AmqpRunner(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(
                TOPIC_EXCHANGE_NAME,
                "foo.bar.baz",
                new FeatureDto(null, 0, "Verified Accounts", "verified-accounts", null, emptySet(), FeatureType.BOOLEAN, "123456789")
        );
    }
}
