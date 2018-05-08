package io.letitroll.core.targeting.mapper;

import io.letitroll.core.feature.domain.Feature;
import io.letitroll.core.targeting.domain.FeatureTargeting;
import io.letitroll.core.targeting.dto.FeatureTargetingDto;
import io.letitroll.core.user.dto.UserDto;
import io.letitroll.core.user.mapper.UserEntity2DtoMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
public class LaboriousFeatureTargetingEntity2DtoMapper implements FeatureTargetingEntity2DtoMapper {

    private final UserEntity2DtoMapper userMapper;

    @Autowired
    public LaboriousFeatureTargetingEntity2DtoMapper(final UserEntity2DtoMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public FeatureTargetingDto map(@NonNull final FeatureTargeting source) {
        requireNonNull(source, "source must not be null!");
        final String id = Optional.ofNullable(source.getId())
                .map(ObjectId::toString)
                .orElse(null);
        final String featureId = Optional.of(source.getFeature())
                .map(Feature::getId)
                .map(ObjectId::toString)
                .orElseThrow(() -> new IllegalStateException("Expected Feature had non null ID"));
        final ZonedDateTime featureAddedTime = Optional.of(source.getFeature())
                .map(Feature::getId)
                .map(ObjectId::getTimestamp)
                .map(Instant::ofEpochSecond)
                .map(instant -> ZonedDateTime.ofInstant(instant, ZoneOffset.UTC))
                .orElseThrow(() -> new IllegalStateException("Expected Feature had non null ID"));
        final UserDto maintainer = userMapper.map(source.getFeature().getMaintainer());

        return new FeatureTargetingDto(
                id,
                source.getVersion(),
                featureId,
                source.getFeature().getName(),
                featureAddedTime,
                source.getFeature().getKey(),
                source.getFeature().getDescription(),
                maintainer,
                source.isTurnedOn()
        );
    }
}
