package io.letitroll.be.user.service;

import io.letitroll.be.user.repository.UserRepository;
import io.letitroll.common.user.mapper.UserEntity2DetailsDtoMapper;
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
    private final UserEntity2DetailsDtoMapper userMapper;

    @Autowired
    public UserService(final UserRepository userRepository, final UserEntity2DetailsDtoMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Mono<UserDetails> findByUsername(@NonNull final String username) {
        Objects.requireNonNull(username, "username must not be null!");
        return userRepository.findByUsername(username).map(userMapper::map);
    }
}
