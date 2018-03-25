package io.letitroll.be.feature.mapper.impl;

import io.letitroll.be.feature.domain.Feature;
import io.letitroll.be.feature.dto.FeatureDto;
import io.letitroll.be.feature.mapper.FeatureMapper;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Component
public class LaboriousFeatureMapper implements FeatureMapper {

    @Override
    public FeatureDto toDto(@NotNull final Feature entity) {
        Objects.requireNonNull(entity, "entity must not be null!");
        final String id = Optional.ofNullable(entity.getId())
                .map(ObjectId::toString)
                .orElse(null);
        return new FeatureDto(id, entity.getVersion(), entity.getName());
    }

    @Override
    public Feature toEntity(@NotNull final FeatureDto dto) {
        Objects.requireNonNull(dto, "dto must not be null!");
        final ObjectId id = Optional.ofNullable(dto.getId())
                .map(ObjectId::new)
                .orElse(null);
        return new Feature(id, dto.getVersion(), dto.getName());
    }
}
