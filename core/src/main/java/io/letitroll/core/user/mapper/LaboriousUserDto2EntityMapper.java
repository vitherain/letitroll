package io.letitroll.core.user.mapper;

import io.letitroll.core.user.domain.User;
import io.letitroll.core.user.dto.UserDetailsDto;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
public class LaboriousUserDto2EntityMapper implements UserDto2EntityMapper {

    @Override
    public User map(@NonNull final UserDetailsDto source) {
        requireNonNull(source, "source must not be null!");
        final ObjectId id = Optional.ofNullable(source.getId())
                .map(ObjectId::new)
                .orElse(null);
        return new User(id, source.getVersion(), source.getUsername(), source.getPassword(), source.getRole());
    }
}
