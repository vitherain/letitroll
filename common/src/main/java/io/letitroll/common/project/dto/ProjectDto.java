package io.letitroll.common.project.dto;

/**
 * Instances are immutable.
 */
public final class ProjectDto {

    private final String id;
    private final long version;
    private final String name;

    public ProjectDto(final String id, final long version, final String name) {
        this.id = id;
        this.version = version;
        this.name = name;
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
}
