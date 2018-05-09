package io.letitroll.be.feature.controller;

import io.letitroll.be.api.ApiUrls;
import io.letitroll.core.errorhandling.exception.SecurityViolationException;
import io.letitroll.core.feature.mapper.FeatureEntity2DtoMapper;
import io.letitroll.core.feature.repository.FeatureRepository;
import io.letitroll.core.targeting.dto.FeatureTargetingDto;
import io.letitroll.core.targeting.mapper.FeatureTargetingEntity2DtoMapper;
import io.letitroll.core.targeting.repository.FeatureTargetingRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureController {

    private final FeatureRepository featureRepository;
    private final FeatureEntity2DtoMapper featureEntity2DtoMapper;
    private final FeatureTargetingRepository featureTargetingRepository;
    private final FeatureTargetingEntity2DtoMapper featureTargetingEntity2DtoMapper;

    @Autowired
    public FeatureController(
            final FeatureRepository featureRepository,
            final FeatureTargetingRepository featureTargetingRepository,
            final FeatureEntity2DtoMapper featureEntity2DtoMapper,
            final FeatureTargetingEntity2DtoMapper featureTargetingEntity2DtoMapper) {
        this.featureRepository = featureRepository;
        this.featureTargetingRepository = featureTargetingRepository;
        this.featureEntity2DtoMapper = featureEntity2DtoMapper;
        this.featureTargetingEntity2DtoMapper = featureTargetingEntity2DtoMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = ApiUrls.ENVIRONMENTS_ENVIRONMENT_TARGETED_FEATURES)
    public Page<FeatureTargetingDto> getTargetedFeaturesByEnvironment(
            @PathVariable("environmentId") final String environmentId,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
            final Pageable pageable) {

        // TODO some assert that environment belongs to project?
        return featureTargetingRepository.findAllByEnvironmentId(new ObjectId(environmentId), pageable)
                .map(featureTargetingEntity2DtoMapper::map);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = ApiUrls.PROJECTS_PROJECT_FEATURES_FEATURE)
    public ResponseEntity<?> deleteProjectFeature(
            @PathVariable final String projectId,
            @PathVariable final String featureId) {

        this.assertFeatureBelongsToProject(featureId, projectId);
        featureRepository.deleteById(new ObjectId(featureId));
        return ResponseEntity.noContent().build();
    }

    private void assertFeatureBelongsToProject(@NonNull final String featureId, @NonNull final String projectId) {
        featureRepository.findById(new ObjectId(featureId))
                .ifPresent(feature -> {
                    final boolean belongsToProject = new ObjectId(projectId).equals(feature.getProject().getId());
                    if (!belongsToProject) {
                        throw new SecurityViolationException();
                    }
                });
    }
}
