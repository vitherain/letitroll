package io.letitroll.core.user.mapper;

import io.letitroll.core.user.domain.User;
import io.letitroll.core.user.dto.UserDto;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class LaboriousUserEntity2DtoMapper implements UserEntity2DtoMapper {

    @Override
    public UserDto map(@NonNull final User source) {
        Objects.requireNonNull(source, "source must not be null!");
        final String id = Optional.ofNullable(source.getId())
                .map(ObjectId::toString)
                .orElse(null);
        Objects.requireNonNull(id, "user id must not be null!");
        return new UserDto(id, source.getUsername());
    }
}
