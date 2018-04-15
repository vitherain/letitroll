package io.letitroll.core.user.mapper;

import io.letitroll.core.user.domain.User;
import io.letitroll.core.user.dto.UserDetailsDto;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class LaboriousUserEntity2DetailsDtoMapper implements UserEntity2DetailsDtoMapper {

    @Override
    public UserDetailsDto map(@NonNull final User source) {
        Objects.requireNonNull(source, "source must not be null!");
        final String id = Optional.ofNullable(source.getId())
                .map(ObjectId::toString)
                .orElse(null);
        return new UserDetailsDto(id, source.getUsername(), source.getPassword(), source.getRole());
    }
}
