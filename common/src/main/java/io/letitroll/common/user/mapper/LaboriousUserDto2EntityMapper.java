package io.letitroll.common.user.mapper;

import io.letitroll.common.user.domain.User;
import io.letitroll.common.user.dto.UserDetailsDto;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class LaboriousUserDto2EntityMapper implements UserDto2EntityMapper {

    @Override
    public User map(@NonNull final UserDetailsDto source) {
        Objects.requireNonNull(source, "source must not be null!");
        final ObjectId id = Optional.ofNullable(source.getId())
                .map(ObjectId::new)
                .orElse(null);
        return new User(id, source.getUsername(), source.getPassword(), source.getRole());
    }
}
