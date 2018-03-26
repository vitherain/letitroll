package io.letitroll.be.user.service;

import io.letitroll.be.user.mapper.UserMapper;
import io.letitroll.be.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Service
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Mono<UserDetails> findByUsername(@NotNull final String username) {
        Objects.requireNonNull(username, "username must not be null!");
        return userRepository.findByUsername(username).map(userMapper::toDto);
    }
}
