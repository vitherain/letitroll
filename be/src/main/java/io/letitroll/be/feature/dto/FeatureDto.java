package io.letitroll.be.feature.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class FeatureDto {

    private final String id;
    private final long version;
    private final String name;

    @JsonCreator
    public FeatureDto(
            @JsonProperty("id") final String id,
            @JsonProperty("version") final long version,
            @JsonProperty("name") final String name) {

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
