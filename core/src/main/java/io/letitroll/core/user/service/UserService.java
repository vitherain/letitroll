package io.letitroll.core.user.service;

import io.letitroll.core.user.dto.UserDetailsDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDetailsDto getCurrentUser();

    String getCurrentUserId();
}
