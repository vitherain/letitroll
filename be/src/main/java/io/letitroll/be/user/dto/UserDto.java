package io.letitroll.be.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.letitroll.be.user.domain.Role;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * Instances are immutable.
 */
public final class UserDto implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final String username;
    @JsonIgnore
    private final String password;
    @JsonIgnore
    private final Role role;

    public UserDto(@Nullable final String id, @NonNull final String username, @NonNull final String password, @NonNull final Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Nullable
    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @NonNull
    @Override
    public String getPassword() {
        return password;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @NonNull
    public Role getRole() {
        return role;
    }
}
