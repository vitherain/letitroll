package io.letitroll.be.feature.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public final class Feature {

    @Id
    private final String uuid;
    @Version
    private final long version;
    private final String name;

    public Feature(final String name) {
        this(null, 0, name);
    }

    @PersistenceConstructor
    public Feature(final String uuid, final long version, final String name) {
        this.uuid = uuid;
        this.version = version;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public long getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }
}
