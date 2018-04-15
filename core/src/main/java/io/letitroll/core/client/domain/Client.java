package io.letitroll.core.client.domain;

import io.letitroll.core.environment.Environment;
import io.letitroll.core.project.domain.Project;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static io.letitroll.core.errorhandling.constants.ValidationConstants.EMAIL_REGEX;
import static io.letitroll.core.errorhandling.constants.ValidationConstants.IP_ADDRESS_REGEX;

/**
 * Instances are immutable.
 */
@Document(collection = "clients")
public final class Client {

    @Id
    private final ObjectId id;
    @Version
    private final long version;
    @DBRef
    @NotNull
    private final Project project;
    @DBRef
    @NotNull
    private final Environment environment;
    @NotNull
    @Size(max = 255)
    private final String key;
    @Pattern(regexp = IP_ADDRESS_REGEX)
    private final String ip;
    @Pattern(regexp = EMAIL_REGEX)
    private final String email;
    private final boolean anonymous;

    public Client(
            @NonNull final Project project,
            @NonNull final Environment environment,
            @NonNull final String key,
            final boolean anonymous) {
        this(null, 0, project, environment, key, null, null, anonymous);
    }

    @PersistenceConstructor
    public Client(
            @Nullable final ObjectId id,
            final long version,
            @NonNull final Project project,
            @NonNull final Environment environment,
            @NonNull final String key,
            @Nullable final String ip,
            @Nullable final String email,
            final boolean anonymous) {

        this.id = id;
        this.version = version;
        this.project = project;
        this.environment = environment;
        this.key = key;
        this.ip = ip;
        this.email = email;
        this.anonymous = anonymous;
    }

    @Nullable
    public ObjectId getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @NonNull
    public Project getProject() {
        return project;
    }

    @NonNull
    public Environment getEnvironment() {
        return environment;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    @Nullable
    public String getIp() {
        return ip;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public boolean isAnonymous() {
        return anonymous;
    }
}
