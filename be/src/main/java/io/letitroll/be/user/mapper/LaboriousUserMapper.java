package io.letitroll.be.user.mapper;

import io.letitroll.be.user.domain.User;
import io.letitroll.be.user.dto.UserDto;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class LaboriousUserMapper implements UserMapper {

    @Override
    public UserDto a2B(@NonNull final User entity) {
        Objects.requireNonNull(entity, "entity must not be null!");
        final String id = Optional.ofNullable(entity.getId())
                .map(ObjectId::toString)
                .orElse(null);
        return new UserDto(id, entity.getUsername(), entity.getPassword(), entity.getRole());
    }

    @Override
    public User b2A(@NonNull final UserDto dto) {
        Objects.requireNonNull(dto, "dto must not be null!");
        final ObjectId id = Optional.ofNullable(dto.getId())
                .map(ObjectId::new)
                .orElse(null);
        return new User(id, dto.getUsername(), dto.getPassword(), dto.getRole());
    }
}
