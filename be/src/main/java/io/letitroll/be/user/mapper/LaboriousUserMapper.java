package io.letitroll.be.user.mapper;

import io.letitroll.be.user.dto.UserDto;
import io.letitroll.common.user.domain.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Component
public class LaboriousUserMapper implements UserMapper {

    @Override
    public UserDto toDto(@NotNull final User entity) {
        Objects.requireNonNull(entity, "entity must not be null!");
        final String id = Optional.ofNullable(entity.getId())
                .map(ObjectId::toString)
                .orElse(null);
        return new UserDto(id, entity.getUsername(), entity.getPassword(), entity.getRole());
    }

    @Override
    public User toEntity(@NotNull final UserDto dto) {
        Objects.requireNonNull(dto, "dto must not be null!");
        final ObjectId id = Optional.ofNullable(dto.getId())
                .map(ObjectId::new)
                .orElse(null);
        return new User(id, dto.getUsername(), dto.getPassword(), dto.getRole());
    }
}
