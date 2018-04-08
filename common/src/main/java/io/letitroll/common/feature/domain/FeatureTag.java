package io.letitroll.common.feature.domain;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Instances are immutable.
 */
public final class FeatureTag {

    @NotNull
    @Size(max = 50)
    private final String name;

    @PersistenceConstructor
    public FeatureTag(@NonNull final String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
