package io.letitroll.core.targeting.dto;

import io.letitroll.core.user.dto.UserDto;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

/**
 * Instances are immutable.
 */
public final class FeatureTargetingDto {

    private final String id;
    private final long version;
    private final String featureId;
    private final String featureName;
    private final ZonedDateTime featureAddedTime;
    private final String featureKey;
    private final String featureDescription;
    private final UserDto featureMaintainer;
    private final boolean turnedOn;

    public FeatureTargetingDto(
            @Nullable final String id,
            final long version,
            @NonNull final String featureId,
            @NonNull final String featureName,
            @NonNull final ZonedDateTime featureAddedTime,
            @NonNull final String featureKey,
            @Nullable final String featureDescription,
            @NonNull final UserDto featureMaintainer,
            final boolean turnedOn) {

        this.id = id;
        this.version = version;
        this.featureId = featureId;
        this.featureName = featureName;
        this.featureAddedTime = featureAddedTime;
        this.featureKey = featureKey;
        this.featureDescription = featureDescription;
        this.featureMaintainer = featureMaintainer;
        this.turnedOn = turnedOn;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @NonNull
    public String getFeatureId() {
        return featureId;
    }

    @NonNull
    public String getFeatureName() {
        return featureName;
    }

    @NonNull
    public ZonedDateTime getFeatureAddedTime() {
        return featureAddedTime;
    }

    @NonNull
    public String getFeatureKey() {
        return featureKey;
    }

    @Nullable
    public String getFeatureDescription() {
        return featureDescription;
    }

    @NonNull
    public UserDto getFeatureMaintainer() {
        return featureMaintainer;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }
}
