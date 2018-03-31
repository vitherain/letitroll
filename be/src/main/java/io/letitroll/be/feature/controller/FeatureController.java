package io.letitroll.be.feature.controller;

import io.letitroll.be.api.ApiUrls;
import io.letitroll.be.feature.dto.FeatureDto;
import io.letitroll.be.feature.mapper.FeatureMapper;
import io.letitroll.be.feature.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FeatureController {

    private final FeatureRepository featureRepository;
    private final FeatureMapper featureMapper;

    @Autowired
    public FeatureController(final FeatureRepository featureRepository, final FeatureMapper featureMapper) {
        this.featureRepository = featureRepository;
        this.featureMapper = featureMapper;
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = ApiUrls.API_GET_ALL_FEATURES)
    public Flux<FeatureDto> getAllFeatures(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
            final Pageable pageable) {

        return featureRepository.findByName("nova pyco", pageable)
                .map(featureMapper::toDto);
    }
}
