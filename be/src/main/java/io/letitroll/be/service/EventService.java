package io.letitroll.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class EventService {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public EventService(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedRate = 3000)
    public void sendEvent() {
        this.eventPublisher.publishEvent(new Event("Message " + ThreadLocalRandom.current().nextInt()));
    }

    public static final class Event {

        private final String message;

        public Event(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
