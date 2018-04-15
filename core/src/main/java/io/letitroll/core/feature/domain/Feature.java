package io.letitroll.core.feature.domain;

import io.letitroll.core.builder.AbstractBuilder;
import io.letitroll.core.project.domain.Project;
import io.letitroll.core.user.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

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
    @NotEmpty
    @Size(max = 50)
    private final String name;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private final String key;
    @Size(max = 1024)
    private final String description;
    @DBRef
    @NotNull
    private final User maintainer;
    private final boolean permanent;
    @NotNull
    private final Set<FeatureTag> tags;
    @NotNull
    private final FeatureType type;
    private final boolean availableToClient;
    @DBRef
    @NotNull
    private final Project project;

    public Feature(@NonNull final FeatureBuilder builder) {
        this(
                builder.id,
                builder.version,
                builder.name,
                builder.key,
                builder.description,
                builder.maintainer,
                builder.permanent,
                builder.tags,
                builder.type,
                builder.availableToClient,
                builder.project
        );
    }

    public Feature(
            @NonNull final String name,
            @NonNull final String key,
            @NonNull final User maintainer,
            @NonNull final Set<FeatureTag> tags,
            @NonNull final FeatureType type,
            @NonNull final Project project) {
        this(null, 0, name, key, null, maintainer, false, tags, type, false, project);
    }

    @PersistenceConstructor
    public Feature(
            @Nullable final ObjectId id,
            final long version,
            @NonNull final String name,
            @NonNull final String key,
            @Nullable final String description,
            @NonNull final User maintainer,
            final boolean permanent,
            @NonNull final Set<FeatureTag> tags,
            @NonNull final FeatureType type,
            final boolean availableToClient,
            @NonNull final Project project) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.key = key;
        this.description = description;
        this.maintainer = maintainer;
        this.permanent = permanent;
        this.tags = tags;
        this.type = type;
        this.availableToClient = availableToClient;
        this.project = project;
    }

    public static FeatureBuilder builder() {
        return new FeatureBuilder();
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

    @Nullable
    public String getDescription() {
        return description;
    }

    @NonNull
    public User getMaintainer() {
        return maintainer;
    }

    public boolean isPermanent() {
        return permanent;
    }

    @NonNull
    public Set<FeatureTag> getTags() {
        return unmodifiableSet(tags);
    }

    @NonNull
    public FeatureType getType() {
        return type;
    }

    public boolean isAvailableToClient() {
        return availableToClient;
    }

    @NonNull
    public Feature name(@NonNull final String name) {
        return new Feature(id, version, name, key, description, maintainer, permanent, tags, type, availableToClient, project);
    }

    @NonNull
    public Project getProject() {
        return project;
    }

    @NonNull
    public Feature project(@NonNull final Project project) {
        return new Feature(id, version, name, key, description, maintainer, permanent, tags, type, availableToClient, project);
    }

    public static final class FeatureBuilder extends AbstractBuilder<Feature> {

        private ObjectId id;
        private long version;
        @NotNull
        @NotEmpty
        @Size(max = 50)
        private String name;
        @NotNull
        @NotEmpty
        @Size(max = 50)
        private String key;
        @Size(max = 1024)
        private String description;
        @NotNull
        private User maintainer;
        private boolean permanent;
        @NotNull
        private Set<FeatureTag> tags;
        @NotNull
        private FeatureType type;
        private boolean availableToClient;
        @NotNull
        private Project project;

        private FeatureBuilder() {
        }

        public FeatureBuilder id(ObjectId id) {
            this.id = id;
            return this;
        }

        public FeatureBuilder version(long version) {
            this.version = version;
            return this;
        }

        public FeatureBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FeatureBuilder key(String key) {
            this.key = key;
            return this;
        }

        public FeatureBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FeatureBuilder maintainer(User maintainer) {
            this.maintainer = maintainer;
            return this;
        }

        public FeatureBuilder permanent(boolean permanent) {
            this.permanent = permanent;
            return this;
        }

        public FeatureBuilder tags(Set<FeatureTag> tags) {
            this.tags = tags;
            return this;
        }

        public FeatureBuilder type(FeatureType type) {
            this.type = type;
            return this;
        }

        public FeatureBuilder availableToClient(boolean availableToClient) {
            this.availableToClient = availableToClient;
            return this;
        }

        public FeatureBuilder project(Project project) {
            this.project = project;
            return this;
        }

        @Override
        protected Feature buildInternal() {
            return new Feature(this);
        }
    }
}
