package io.letitroll.common.environment;

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
@Document(collection = "environments")
public final class Environment {

    @Id
    private final ObjectId id;
    @Version
    private final long version;
    @NotNull
    @Size(max = 50)
    private final String name;
    @DBRef
    @NotNull
    private final Project project;

    public Environment(@NonNull final String name, @NonNull final Project project) {
        this(null, 0, name, project);
    }

    @PersistenceConstructor
    public Environment(@Nullable final ObjectId id, final long version, @NonNull final String name, @NonNull final Project project) {
        this.id = id;
        this.version = version;
        this.name = name;
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
    public Environment name(@NonNull final String name) {
        return new Environment(id, version, name, project);
    }

    @NonNull
    public Project getProject() {
        return project;
    }

    @NonNull
    public Environment project(@NonNull final Project project) {
        return new Environment(id, version, name, project);
    }
}
