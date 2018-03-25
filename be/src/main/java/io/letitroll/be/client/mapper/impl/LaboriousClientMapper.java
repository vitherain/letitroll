package io.letitroll.be.client.mapper.impl;

import io.letitroll.be.client.domain.Client;
import io.letitroll.be.client.dto.ClientDto;
import io.letitroll.be.client.mapper.ClientMapper;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Component
public class LaboriousClientMapper implements ClientMapper {

    @Override
    public ClientDto toDto(@NotNull final Client entity) {
        Objects.requireNonNull(entity, "entity must not be null!");
        final String id = Optional.ofNullable(entity.getId())
                .map(ObjectId::toString)
                .orElse(null);
        return new ClientDto(id, entity.getUsername(), entity.getPassword(), entity.getRole());
    }

    @Override
    public Client toEntity(@NotNull final ClientDto dto) {
        Objects.requireNonNull(dto, "dto must not be null!");
        final ObjectId id = Optional.ofNullable(dto.getId())
                .map(ObjectId::new)
                .orElse(null);
        return new Client(id, dto.getUsername(), dto.getPassword(), dto.getRole());
    }
}
