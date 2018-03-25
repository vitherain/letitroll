package io.letitroll.be.emitter.endpoint;

import io.letitroll.be.emitter.service.EventService;
import io.letitroll.be.feature.domain.Feature;
import io.letitroll.be.feature.repository.FeatureRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class EmitterController {

    private final FeatureRepository repository;

    @Autowired
    public EmitterController(final FeatureRepository repository) {
        this.repository = repository;
    }

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

        repository.save(new Feature("" + ThreadLocalRandom.current().nextInt())).map(feature -> {
                    LoggerFactory.getLogger("erge").info(feature.getId() + " " + feature.getName());
                    repository.save(new Feature(feature.getId(), feature.getVersion(), "nova pyco")).subscribe(feature1 -> {
                        repository.save(new Feature(feature1.getId(), 0, "nova pyco 50")).subscribe();
                    });
                    return feature;
        }
                ).subscribe();
    }
}
