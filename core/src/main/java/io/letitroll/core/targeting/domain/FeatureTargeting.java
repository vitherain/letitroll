package io.letitroll.core.targeting.domain;

import io.letitroll.core.environment.Environment;
import io.letitroll.core.feature.domain.Feature;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * Instances are immutable.
 */
@Document(collection = "targetings")
public final class FeatureTargeting {

    @Id
    private final ObjectId id;
    @Version
    private final long version;
    @DBRef
    @NotNull
    private final Feature feature;
    @DBRef
    @NotNull
    private final Environment environment;
    private final boolean turnedOn;
    private final boolean onValue;
    private final boolean offValue;

    @PersistenceConstructor
    public FeatureTargeting(
            @Nullable final ObjectId id,
            final long version,
            @NonNull final Feature feature,
            @NonNull final Environment environment,
            final boolean turnedOn,
            final boolean onValue,
            final boolean offValue) {

        this.id = id;
        this.version = version;
        this.feature = feature;
        this.environment = environment;
        this.turnedOn = turnedOn;
        this.onValue = onValue;
        this.offValue = offValue;
    }

    @Nullable
    public ObjectId getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @NonNull
    public Feature getFeature() {
        return feature;
    }

    @NonNull
    public Environment getEnvironment() {
        return environment;
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
