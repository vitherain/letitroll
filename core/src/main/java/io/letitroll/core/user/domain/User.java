package io.letitroll.core.user.domain;

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
@Document(collection = "users")
public final class User {

    @Id
    private final ObjectId id;
    @Version
    private final long version;
    @NotNull
    @Indexed(unique = true)
    @Size(max = 50)
    private final String username;
    @NotNull
    private final String password;
    @NotNull
    private final Role role;

    public User(@NonNull final String username, @NonNull final String password, @NonNull final Role role) {
        this(null, 0, username, password, role);
    }

    @PersistenceConstructor
    public User(@Nullable final ObjectId id, final long version, @NonNull final String username, @NonNull final String password, @NonNull final Role role) {
        this.id = id;
        this.version = version;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Nullable
    public ObjectId getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public Role getRole() {
        return role;
    }
}
