package io.letitroll.be.feature.controller;

import io.letitroll.be.feature.domain.Feature;
import io.letitroll.be.feature.repository.FeatureRepository;
import io.letitroll.be.shared.api.ApiUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FeatureController {

    @Autowired
    private FeatureRepository featureRepository;

    @GetMapping(value = ApiUrls.API_GET_ALL_FEATURES)
    public Flux<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }
}
