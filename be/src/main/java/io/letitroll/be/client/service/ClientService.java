package io.letitroll.be.client.service;

import io.letitroll.be.client.mapper.ClientMapper;
import io.letitroll.be.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Service
public class ClientService implements ReactiveUserDetailsService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(final ClientRepository clientRepository, final ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Mono<UserDetails> findByUsername(@NotNull final String username) {
        Objects.requireNonNull(username, "username must not be null!");
        return clientRepository.findByUsername(username).map(clientMapper::toDto);
    }
}
