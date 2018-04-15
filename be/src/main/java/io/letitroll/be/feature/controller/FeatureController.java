package io.letitroll.be.feature.controller;

import io.letitroll.be.api.ApiUrls;
import io.letitroll.core.errorhandling.exception.SecurityViolationException;
import io.letitroll.core.feature.dto.FeatureDto;
import io.letitroll.core.feature.mapper.FeatureEntity2DtoMapper;
import io.letitroll.core.feature.repository.FeatureRepository;
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

    @Autowired
    public FeatureController(final FeatureRepository featureRepository, final FeatureEntity2DtoMapper featureEntity2DtoMapper) {
        this.featureRepository = featureRepository;
        this.featureEntity2DtoMapper = featureEntity2DtoMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = ApiUrls.PROJECT_FEATURES)
    public Page<FeatureDto> getProjectFeatures(
            @PathVariable final String projectId,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
            final Pageable pageable) {

        return featureRepository.findAllByProjectId(new ObjectId(projectId), pageable)
                .map(featureEntity2DtoMapper::map);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = ApiUrls.PROJECT_FEATURE)
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
