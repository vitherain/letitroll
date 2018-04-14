package io.letitroll.common.feature.mapper;

import io.letitroll.common.feature.domain.Feature;
import io.letitroll.common.feature.domain.FeatureTag;
import io.letitroll.common.feature.dto.FeatureDto;
import io.letitroll.common.feature.dto.FeatureTagDto;
import io.letitroll.common.project.domain.Project;
import io.letitroll.common.user.dto.UserDto;
import io.letitroll.common.user.mapper.UserEntity2DtoMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;

@Component
public class LaboriousFeatureEntity2DtoMapper implements FeatureEntity2DtoMapper {

    private final UserEntity2DtoMapper userMapper;

    @Autowired
    public LaboriousFeatureEntity2DtoMapper(final UserEntity2DtoMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public FeatureDto map(@NonNull final Feature source) {
        Objects.requireNonNull(source, "source must not be null!");
        final String id = Optional.ofNullable(source.getId())
                .map(ObjectId::toString)
                .orElse(null);
        final ZonedDateTime addedTime = Optional.ofNullable(source.getId())
                .map(ObjectId::getTimestamp)
                .map(Instant::ofEpochSecond)
                .map(instant -> ZonedDateTime.ofInstant(instant, ZoneOffset.UTC))
                .orElse(null);
        final String projectId = Optional.of(source.getProject())
                .map(Project::getId)
                .map(ObjectId::toString)
                .orElse(null);
        final UserDto maintainer = userMapper.map(source.getMaintainer());

        return new FeatureDto(
                id,
                addedTime,
                source.getVersion(),
                source.getName(),
                source.getKey(),
                source.getDescription(),
                maintainer,
                source.isPermanent(),
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
