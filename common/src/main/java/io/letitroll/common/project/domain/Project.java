package io.letitroll.common.project.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Instances are immutable.
 */
@Document(collection = "projects")
public final class Project {

    @Id
    private final ObjectId id;
    @Version
    private final long version;
    @Indexed(unique = true)
    @NotNull
    @Size(max = 50)
    private final String name;

    public Project(@NonNull final String name) {
        this(null, 0, name);
    }

    @PersistenceConstructor
    public Project(@Nullable final ObjectId id, final long version, @NonNull final String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    @Nullable
    public ObjectId getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public Project name(@NonNull final String name) {
        return new Project(id, version, name);
    }
}
