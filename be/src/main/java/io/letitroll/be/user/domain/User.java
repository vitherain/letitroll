package io.letitroll.be.user.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public final class User {

    @Id
    private final ObjectId id;
    private final String username;
    private final Role role;

    public User(final String username, final Role role) {
        this(null, username, role);
    }

    @PersistenceConstructor
    public User(final ObjectId id, final String username, final Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public ObjectId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}
