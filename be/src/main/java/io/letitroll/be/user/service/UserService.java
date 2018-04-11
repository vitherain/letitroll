package io.letitroll.be.user.service;

import io.letitroll.be.user.mapper.UserEntity2DtoMapper;
import io.letitroll.be.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final UserEntity2DtoMapper userEntity2DtoMapper;

    @Autowired
    public UserService(final UserRepository userRepository, final UserEntity2DtoMapper userEntity2DtoMapper) {
        this.userRepository = userRepository;
        this.userEntity2DtoMapper = userEntity2DtoMapper;
    }

    @Override
    public Mono<UserDetails> findByUsername(@NonNull final String username) {
        Objects.requireNonNull(username, "username must not be null!");
        return userRepository.findByUsername(username).map(userEntity2DtoMapper::map);
    }
}
