package io.letitroll.core.user.mapper;

import io.letitroll.core.user.domain.User;
import io.letitroll.core.user.dto.UserDto;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
public class LaboriousUserEntity2DtoMapper implements UserEntity2DtoMapper {

    @Override
    public UserDto map(@NonNull final User source) {
        requireNonNull(source, "source must not be null!");
        final String id = Optional.ofNullable(source.getId())
                .map(ObjectId::toString)
                .orElse(null);
        requireNonNull(id, "user id must not be null!");
        return new UserDto(id, source.getVersion(), source.getUsername());
    }
}
