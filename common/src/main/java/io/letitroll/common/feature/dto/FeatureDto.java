package io.letitroll.common.feature.dto;

/**
 * Instances are immutable.
 */
public final class FeatureDto {

    private final String id;
    private final long version;
    private final String name;
    private final String projectId;

    public FeatureDto(final String id, final long version, final String name, final String projectId) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getProjectId() {
        return projectId;
    }
}
