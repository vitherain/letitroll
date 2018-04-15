package io.letitroll.core.user.dto;

import org.springframework.lang.NonNull;

/**
 * Instances are immutable.
 */
public final class UserDto {

    private final String id;
    private final long version;
    private final String username;

    public UserDto(@NonNull final String id, final long version, @NonNull final String username) {
        this.id = id;
        this.version = version;
        this.username = username;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @NonNull
    public String getUsername() {
        return username;
    }
}
