package io.letitroll.be.user.service;

import io.letitroll.be.user.repository.UserRepository;
import io.letitroll.common.user.domain.User;
import io.letitroll.common.user.mapper.UserEntity2DetailsDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserEntity2DetailsDtoMapper userMapper;

    @Autowired
    public UserService(final UserRepository userRepository, final UserEntity2DetailsDtoMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull final String username) {
        Objects.requireNonNull(username, "username must not be null!");
        final User user = userRepository.findByUsername(username);
        return userMapper.map(user);
    }
}
