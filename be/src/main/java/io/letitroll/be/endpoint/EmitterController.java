package io.letitroll.be.endpoint;

import io.letitroll.be.service.EventService;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmitterController {

    private List<SseEmitter> emitters = new ArrayList<>();

    @GetMapping("/emitter")
    public SseEmitter getSseEmitter() throws IOException {
        final SseEmitter emitter = new SseEmitter();
        emitter.send(SseEmitter.event().data("Message #1"));
        emitters.add(emitter);
        return emitter;
    }

    @EventListener
    public void onMemoryInfo(final EventService.Event event) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(event);
            }
            catch (final Exception e) {
                deadEmitters.add(emitter);
            }
        });

        this.emitters.removeAll(deadEmitters);
    }
}
