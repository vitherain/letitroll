package io.letitroll.be.feature.controller;

import io.letitroll.be.api.ApiUrls;
import io.letitroll.be.feature.repository.FeatureRepository;
import io.letitroll.common.feature.dto.FeatureDto;
import io.letitroll.common.feature.mapper.FeatureEntity2DtoMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureController {

    private final FeatureRepository featureRepository;
    private final FeatureEntity2DtoMapper featureEntity2DtoMapper;

    @Autowired
    public FeatureController(final FeatureRepository featureRepository, final FeatureEntity2DtoMapper featureEntity2DtoMapper) {
        this.featureRepository = featureRepository;
        this.featureEntity2DtoMapper = featureEntity2DtoMapper;
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = ApiUrls.PROJECT_FEATURES)
    public Page<FeatureDto> getProjectFeatures(
            @PathVariable final String projectId,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
            final Pageable pageable) {

        return featureRepository.findByProjectId(new ObjectId(projectId), pageable)
                .map(featureEntity2DtoMapper::map);
    }
}
