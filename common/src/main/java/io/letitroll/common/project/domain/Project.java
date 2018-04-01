package io.letitroll.common.project.domain;

import io.letitroll.common.feature.domain.Feature;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "projects")
public final class Project implements Serializable {

    @Id
    private final ObjectId id;
    @Version
    private final long version;
    @Indexed(unique = true)
    private final String name;
    @DBRef
    private final List<Feature> features;

    public Project(final String name) {
        this(null, 0, name, new ArrayList<>());
    }

    @PersistenceConstructor
    public Project(final ObjectId id, final long version, final String name, final List<Feature> features) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.features = features;
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

    public Project name(final String name) {
        return new Project(id, version, name, features);
    }

    public List<Feature> getFeatures() {
        return new ArrayList<>(features);
    }
}
