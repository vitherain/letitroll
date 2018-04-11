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
public class LaboriousFeatureEntity2DtoMapper implements FeatureEntity2DtoMapper {

    @Override
    public FeatureDto map(@NonNull final Feature source) {
        Objects.requireNonNull(source, "source must not be null!");
        final String id = Optional.ofNullable(source.getId())
                .map(ObjectId::toString)
                .orElse(null);
        final String projectId = Optional.of(source.getProject())
                .map(Project::getId)
                .map(ObjectId::toString)
                .orElse(null);

        return new FeatureDto(
                id,
                source.getVersion(),
                source.getName(),
                source.getKey(),
                source.getDescription(),
                source.getTags().stream().map(this::mapTagToDto).collect(toSet()),
                source.getType(),
                source.isAvailableToClient(),
                projectId
        );
    }

    private FeatureTagDto mapTagToDto(@NonNull final FeatureTag tag) {
        return new FeatureTagDto(tag.getName());
    }
}
