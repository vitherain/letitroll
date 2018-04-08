package io.letitroll.client.emitter.endpoint;

import io.letitroll.client.emitter.repository.FeatureRepository;
import io.letitroll.client.emitter.repository.ProjectRepository;
import io.letitroll.client.emitter.service.EventService;
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

@RestController
public class EmitterController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private ProjectRepository projectRepository;

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

        /*final Project project = projectRepository.save(new Project("Zonky"));

        final Feature feature1 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature2 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature3 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature4 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature5 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature6 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature7 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature8 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature9 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature10 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature11 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature12 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature13 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature14 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature15 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature16 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature17 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature18 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature19 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature20 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature21 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature22 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final Feature feature23 = featureRepository.save(new Feature("" + ThreadLocalRandom.current().nextInt(), "" + ThreadLocalRandom.current().nextInt(), null, emptySet(), FeatureType.BOOLEAN, project));
        final List<Feature> features = Arrays.asList(feature1, feature2, feature3, feature4, feature5, feature6, feature7,
                feature8, feature9, feature10, feature11, feature12, feature13, feature14, feature15, feature16, feature17, feature18,
                feature19, feature20, feature21, feature22, feature23);
        featureRepository.saveAll(features);*/
        /*featureRepository.save(new Feature("Prdel vody", project));*/

        /*Project project = projectRepository.findById(new ObjectId("5ac1e829ba2b4612149738a2")).get();
        System.out.println(project);
        Feature feature = featureRepository.findById(new ObjectId("5ac1e829ba2b4612149738ab")).get();
        System.out.println(feature);*/
    }
}
