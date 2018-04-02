package io.letitroll.common.feature.dto;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Instances are immutable.
 */
public final class FeatureDto {

    private final String id;
    private final long version;
    private final String name;
    private final String projectId;

    public FeatureDto(@Nullable final String id, final long version, @NonNull final String name, @NonNull final String projectId) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.projectId = projectId;
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

    @NonNull
    public String getProjectId() {
        return projectId;
    }
}
