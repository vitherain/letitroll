package io.letitroll.common.user.service;

import io.letitroll.common.user.dto.UserDetailsDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDetailsDto getCurrentUser();

    String getCurrentUserId();
}
