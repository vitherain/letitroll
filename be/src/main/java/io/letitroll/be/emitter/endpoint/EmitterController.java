package io.letitroll.be.emitter.endpoint;

import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class EmitterController {

    /*private final FeatureRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public EmitterController(final FeatureRepository repository, final UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        //userRepository.save(new User("reader", "password", Role.READER)).subscribe();
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

        *//*repository.save(new Feature("" + ThreadLocalRandom.current().nextInt())).map(feature -> {
                    LoggerFactory.getLogger("erge").info(feature.getId() + " " + feature.getName());
                    repository.save(new Feature(feature.getId(), feature.getVersion(), "nova pyco")).subscribe(feature1 -> {
                        repository.save(new Feature(feature1.getId(), 0, "nova pyco 50")).subscribe();
                    });
                    return feature;
        }
                ).subscribe();*//*
    }*/
}
