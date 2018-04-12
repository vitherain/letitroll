package io.letitroll.common.targeting.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * Instances are immutable.
 */
@Document(collection = "targetings_history")
public final class FeatureTargetingHistory {

    @Id
    private final ObjectId id;
    @DBRef
    @NotNull
    private final FeatureTargeting targeting;
    private final boolean turnedOn;
    private final boolean onValue;
    private final boolean offValue;

    @PersistenceConstructor
    public FeatureTargetingHistory(
            @Nullable final ObjectId id,
            @NonNull final FeatureTargeting targeting,
            final boolean turnedOn,
            final boolean onValue,
            final boolean offValue) {

        this.id = id;
        this.targeting = targeting;
        this.turnedOn = turnedOn;
        this.onValue = onValue;
        this.offValue = offValue;
    }

    @Nullable
    public ObjectId getId() {
        return id;
    }

    @NonNull
    public FeatureTargeting getTargeting() {
        return targeting;
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
