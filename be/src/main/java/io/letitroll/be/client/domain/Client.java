package io.letitroll.be.client.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public final class Client {

    @Id
    private final ObjectId id;
    private final String username;
    private final String password;
    private final Role role;

    public Client(final String username, final String password, final Role role) {
        this(null, username, password, role);
    }

    @PersistenceConstructor
    public Client(final ObjectId id, final String username, final String password, final Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public ObjectId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
