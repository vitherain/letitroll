package io.letitroll.common.feature.mapper;

import io.letitroll.common.feature.domain.Feature;
import io.letitroll.common.feature.domain.FeatureTag;
import io.letitroll.common.feature.dto.FeatureDto;
import io.letitroll.common.feature.dto.FeatureTagDto;
import io.letitroll.common.project.domain.Project;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;

@Component
public class LaboriousFeatureMapper implements FeatureMapper {

    @Override
    public FeatureDto a2B(@NonNull final Feature entity) {
        Objects.requireNonNull(entity, "entity must not be null!");
        final String id = Optional.ofNullable(entity.getId())
                .map(ObjectId::toString)
                .orElse(null);
        final String projectId = Optional.ofNullable(entity.getProject())
                .map(Project::getId)
                .map(ObjectId::toString)
                .orElse(null);
        return new FeatureDto(
                id,
                entity.getVersion(),
                entity.getName(),
                entity.getKey(),
                entity.getDescription(),
                entity.getTags().stream().map(this::mapTagToDto).collect(toSet()),
                entity.getType(),
                entity.isAvailableToClient(),
                projectId);
    }

    @Override
    public Feature b2A(@NonNull final FeatureDto dto) {
        Objects.requireNonNull(dto, "dto must not be null!");
        final ObjectId id = Optional.ofNullable(dto.getId())
                .map(ObjectId::new)
                .orElse(null);
        return new Feature(
                id,
                dto.getVersion(),
                dto.getName(),
                dto.getKey(),
                dto.getDescription(),
                dto.getTags().stream().map(this::mapTagToEntity).collect(toSet()),
                dto.getType(),
                dto.isAvailableToClient(),
                null);
    }

    private FeatureTagDto mapTagToDto(@NonNull final FeatureTag tag) {
        return new FeatureTagDto(tag.getName());
    }

    private FeatureTag mapTagToEntity(@NonNull final FeatureTagDto tag) {
        return new FeatureTag(tag.getName());
    }
}
