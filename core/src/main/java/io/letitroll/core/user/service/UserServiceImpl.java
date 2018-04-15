package io.letitroll.core.user.service;

import io.letitroll.core.user.domain.User;
import io.letitroll.core.user.dto.UserDetailsDto;
import io.letitroll.core.user.mapper.UserEntity2DetailsDtoMapper;
import io.letitroll.core.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEntity2DetailsDtoMapper userMapper;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final UserEntity2DetailsDtoMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull final String username) {
        requireNonNull(username, "username must not be null!");
        final User user = userRepository.findByUsername(username);
        return userMapper.map(user);
    }

    @Override
    public UserDetailsDto getCurrentUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            final Object principal = auth.getPrincipal();
            if (principal instanceof UserDetailsDto) {
                return (UserDetailsDto) principal;
            }
        }
        return null;
    }

    @Override
    public String getCurrentUserId() {
        final UserDetailsDto currentUser = this.getCurrentUser();
        return currentUser == null ? null : currentUser.getId();
    }
}
