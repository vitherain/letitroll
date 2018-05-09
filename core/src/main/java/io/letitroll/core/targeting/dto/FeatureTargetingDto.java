package io.letitroll.core.targeting.dto;

import io.letitroll.core.feature.dto.FeatureDto;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Instances are immutable.
 */
public final class FeatureTargetingDto {

    private final String id;
    private final long version;
    private final FeatureDto feature;
    private final boolean turnedOn;
    private final boolean onValue;
    private final boolean offValue;

    public FeatureTargetingDto(
            @Nullable final String id,
            final long version,
            @NonNull final FeatureDto feature,
            final boolean turnedOn, final boolean onValue, final boolean offValue) {

        this.id = id;
        this.version = version;
        this.feature = feature;
        this.turnedOn = turnedOn;
        this.onValue = onValue;
        this.offValue = offValue;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @NonNull
    public FeatureDto getFeature() {
        return feature;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }

    public boolean isOnValue() {
        return onValue;
    }

    public boolean isOffValue() {
        return offValue;
    }
}
