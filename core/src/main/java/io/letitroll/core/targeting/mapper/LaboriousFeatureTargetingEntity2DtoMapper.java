package io.letitroll.core.targeting.mapper;

import io.letitroll.core.feature.dto.FeatureDto;
import io.letitroll.core.feature.mapper.FeatureEntity2DtoMapper;
import io.letitroll.core.targeting.domain.FeatureTargeting;
import io.letitroll.core.targeting.dto.FeatureTargetingDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
public class LaboriousFeatureTargetingEntity2DtoMapper implements FeatureTargetingEntity2DtoMapper {

    private final FeatureEntity2DtoMapper featureMapper;

    @Autowired
    public LaboriousFeatureTargetingEntity2DtoMapper(final FeatureEntity2DtoMapper featureMapper) {
        this.featureMapper = featureMapper;
    }

    @Override
    public FeatureTargetingDto map(@NonNull final FeatureTargeting source) {
        requireNonNull(source, "source must not be null!");
        final String id = Optional.ofNullable(source.getId())
                .map(ObjectId::toString)
                .orElse(null);
        final FeatureDto feature = featureMapper.map(source.getFeature());

        return new FeatureTargetingDto(
                id,
                source.getVersion(),
                feature,
                source.isTurnedOn(),
                source.isOnValue(),
                source.isOffValue()
        );
    }
}
