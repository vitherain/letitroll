package io.letitroll.core.environment.dto;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Instances are immutable.
 */
public final class EnvironmentDto {

    private final String id;
    private final long version;
    private final String name;

    public EnvironmentDto(@Nullable final String id, final long version, @NonNull final String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
