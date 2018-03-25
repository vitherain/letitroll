package io.letitroll.be.feature.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.letitroll.be.shared.serializer.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "features")
public final class Feature {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private final ObjectId id;
    @Version
    private final long version;
    private final String name;

    public Feature(final String name) {
        this(null, 0, name);
    }

    @PersistenceConstructor
    public Feature(final ObjectId id, final long version, final String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public Feature name(final String name) {
        return new Feature(id, version, name);
    }
}
