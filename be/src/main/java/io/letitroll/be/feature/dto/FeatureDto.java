package io.letitroll.be.feature.dto;

public final class FeatureDto {

    private final String id;
    private final long version;
    private final String name;

    public FeatureDto(final String id, final long version, final String name) {
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
