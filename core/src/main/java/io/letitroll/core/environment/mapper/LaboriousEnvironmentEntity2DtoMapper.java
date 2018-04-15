package io.letitroll.core.environment.mapper;

import io.letitroll.core.environment.domain.Environment;
import io.letitroll.core.environment.dto.EnvironmentDto;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
public class LaboriousEnvironmentEntity2DtoMapper implements EnvironmentEntity2DtoMapper {

    @Override
    public EnvironmentDto map(@NonNull final Environment source) {
        requireNonNull(source, "source must not be null!");
        final String id = Optional.ofNullable(source.getId())
                .map(ObjectId::toString)
                .orElse(null);
        requireNonNull(id, "environment id must not be null!");
        return new EnvironmentDto(id, source.getVersion(), source.getName());
    }
}
