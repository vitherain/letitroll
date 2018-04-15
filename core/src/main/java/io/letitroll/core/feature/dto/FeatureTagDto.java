package io.letitroll.core.feature.dto;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Instances are immutable.
 */
public final class FeatureTagDto {

    @NotNull
    @Size(max = 50)
    private final String name;

    public FeatureTagDto(@NonNull final String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
