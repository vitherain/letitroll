package io.letitroll.common.feature.domain;

import io.letitroll.common.environment.Environment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

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
            @NonNull final ObjectId id,
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

    public ObjectId getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public Feature getFeature() {
        return feature;
    }

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
