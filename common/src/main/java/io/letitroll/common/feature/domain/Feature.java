package io.letitroll.common.feature.domain;

import io.letitroll.common.project.domain.Project;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Instances are immutable.
 */
@Document(collection = "features")
public final class Feature {

    @Id
    private final ObjectId id;
    @Version
    private final long version;
    @NotNull
    @Size(max = 50)
    private final String name;
    @NotNull
    @Size(max = 50)
    private final String key;
    @DBRef
    @NotNull
    private final Project project;

    public Feature(@NonNull final String name, @NonNull final String key, @NonNull final Project project) {
        this(null, 0, name, key, project);
    }

    @PersistenceConstructor
    public Feature(@Nullable final ObjectId id, final long version, @NonNull final String name, @NonNull final String key, @NonNull final Project project) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.key = key;
        this.project = project;
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
    public String getKey() {
        return key;
    }

    public Feature name(@NonNull final String name) {
        return new Feature(id, version, name, key, project);
    }

    @NonNull
    public Project getProject() {
        return project;
    }

    @NonNull
    public Feature project(@NonNull final Project project) {
        return new Feature(id, version, name, key, project);
    }
}
