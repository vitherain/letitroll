package io.letitroll.common.feature.mapper;

import io.letitroll.common.feature.domain.Feature;
import io.letitroll.common.feature.domain.FeatureTag;
import io.letitroll.common.feature.dto.FeatureDto;
import io.letitroll.common.feature.dto.FeatureTagDto;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;

@Component
public class LaboriousFeatureDto2EntityMapper implements FeatureDto2EntityMapper {

    @Override
    public Feature.FeatureBuilder map(@NonNull final FeatureDto source) {
        Objects.requireNonNull(source, "source must not be null!");
        final ObjectId id = Optional.ofNullable(source.getId())
                .map(ObjectId::new)
                .orElse(null);

        return Feature.builder()
                .id(id)
                .version(source.getVersion())
                .name(source.getName())
                .key(source.getKey())
                .description(source.getDescription())
                .tags(source.getTags().stream().map(this::mapTagToEntity).collect(toSet()))
                .type(source.getType())
                .availableToClient(source.isAvailableToClient());
    }

    private FeatureTag mapTagToEntity(@NonNull final FeatureTagDto tag) {
        return new FeatureTag(tag.getName());
    }
}
