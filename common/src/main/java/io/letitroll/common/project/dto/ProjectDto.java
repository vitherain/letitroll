package io.letitroll.common.project.dto;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Instances are immutable.
 */
public final class ProjectDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final long version;
    private final String name;

    public ProjectDto(@Nullable final String id, final long version, @NonNull final String name) {
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
