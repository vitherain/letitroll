package io.letitroll.common.client.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static io.letitroll.common.validation.ValidationConstants.EMAIL_REGEX;
import static io.letitroll.common.validation.ValidationConstants.IP_ADDRESS_REGEX;

/**
 * Instances are immutable.
 */
@Document(collection = "clients")
public final class Client {

    @Id
    private final ObjectId id;
    @Version
    private final long version;
    @NotNull
    @Size(max = 255)
    private final String key;
    @Pattern(regexp = IP_ADDRESS_REGEX)
    private final String ip;
    @Pattern(regexp = EMAIL_REGEX)
    private final String email;
    private final boolean anonymous;

    public Client(@NonNull final String key, final boolean anonymous) {
        this(null, 0, key, null, null, anonymous);
    }

    @PersistenceConstructor
    public Client(
            @Nullable final ObjectId id,
            final long version,
            @NonNull final String key,
            @Nullable final String ip,
            @Nullable final String email,
            final boolean anonymous) {

        this.id = id;
        this.version = version;
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
