package io.letitroll.be.endpoint;

import io.letitroll.be.service.EventService;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class EmitterController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @CrossOrigin
    @GetMapping("/emitter")
    public SseEmitter getSseEmitter() throws IOException {
        final SseEmitter emitter = new SseEmitter();

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));

        emitters.add(emitter);
        return emitter;
    }

    @EventListener
    public void onMemoryInfo(final EventService.Event event) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                LoggerFactory.getLogger(this.getClass()).info("sending message {}", event.getMessage());
                emitter.send(event);
            }
            catch (final Exception e) {
                deadEmitters.add(emitter);
            }
        });

        this.emitters.removeAll(deadEmitters);
    }
}
