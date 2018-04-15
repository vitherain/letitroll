package io.letitroll.core.user.dto;

import org.springframework.lang.NonNull;

/**
 * Instances are immutable.
 */
public final class UserDto {

    private final String id;
    private final String username;

    public UserDto(@NonNull final String id, @NonNull final String username) {
        this.id = id;
        this.username = username;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }
}
