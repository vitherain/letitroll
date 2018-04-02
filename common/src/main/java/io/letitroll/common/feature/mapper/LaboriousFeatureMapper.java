package io.letitroll.common.feature.mapper;

import io.letitroll.common.feature.domain.Feature;
import io.letitroll.common.feature.dto.FeatureDto;
import io.letitroll.common.project.domain.Project;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class LaboriousFeatureMapper implements FeatureMapper {

    @Override
    public FeatureDto toDto(@NonNull final Feature entity) {
        Objects.requireNonNull(entity, "entity must not be null!");
        final String id = Optional.ofNullable(entity.getId())
                .map(ObjectId::toString)
                .orElse(null);
        final String projectId = Optional.ofNullable(entity.getProject())
                .map(Project::getId)
                .map(ObjectId::toString)
                .orElse(null);
        return new FeatureDto(id, entity.getVersion(), entity.getName(), projectId);
    }

    @Override
    public Feature toEntity(@NonNull final FeatureDto dto) {
        Objects.requireNonNull(dto, "dto must not be null!");
        final ObjectId id = Optional.ofNullable(dto.getId())
                .map(ObjectId::new)
                .orElse(null);
        return new Feature(id, dto.getVersion(), dto.getName(), null);
    }
}