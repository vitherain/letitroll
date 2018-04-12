package io.letitroll.common.feature.dto;

import io.letitroll.common.feature.domain.FeatureType;
import io.letitroll.common.user.dto.UserDto;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

/**
 * Instances are immutable.
 */
public final class FeatureDto {

    private final String id;
    private final long version;
    private final String name;
    private final String key;
    private final String description;
    private final UserDto maintainer;
    private final boolean permanent;
    private final Set<FeatureTagDto> tags;
    private final FeatureType type;
    private final boolean availableToClient;
    private final String projectId;

    public FeatureDto(
            @Nullable final String id,
            final long version,
            @NonNull final String name,
            @NonNull final String key,
            @Nullable final String description,
            @NonNull final UserDto maintainer,
            final boolean permanent,
            @NonNull final Set<FeatureTagDto> tags,
            @NonNull final FeatureType type,
            final boolean availableToClient,
            @Nullable final String projectId) {
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
        this.projectId = projectId;
    }

    @Nullable
    public String getId() {
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
    public UserDto getMaintainer() {
        return maintainer;
    }

    public boolean isPermanent() {
        return permanent;
    }

    @NonNull
    public Set<FeatureTagDto> getTags() {
        return unmodifiableSet(tags);
    }

    @NonNull
    public FeatureType getType() {
        return type;
    }

    public boolean isAvailableToClient() {
        return availableToClient;
    }

    @Nullable
    public String getProjectId() {
        return projectId;
    }
}
