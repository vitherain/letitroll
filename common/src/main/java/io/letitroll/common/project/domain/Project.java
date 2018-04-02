package io.letitroll.common.project.domain;

import io.letitroll.common.feature.domain.Feature;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Instances are immutable.
 */
@Document(collection = "projects")
public final class Project implements Serializable {

    @Id
    private final ObjectId id;
    @Version
    private final long version;
    @Indexed(unique = true)
    @NotNull
    @NonNull
    private final String name;

    public Project(final String name) {
        this(null, 0, name);
    }

    @PersistenceConstructor
    public Project(final ObjectId id, final long version, final String name) {
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

    public Project name(@NonNull final String name) {
        return new Project(id, version, name);
    }
}
