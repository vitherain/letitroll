package io.letitroll.core.project.dto;

import io.letitroll.core.builder.AbstractBuilder;
import io.letitroll.core.environment.dto.EnvironmentDto;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Instances are immutable.
 */
public final class ProjectDto {

    private final String id;
    private final long version;
    private final String name;
    private final List<EnvironmentDto> environments;

    public ProjectDto(@NonNull final ProjectDtoBuilder builder) {
        this(builder.id, builder.version, builder.name, builder.environments);
    }

    public ProjectDto(
            @Nullable final String id, 
            final long version, 
            @NonNull final String name, 
            @NonNull final List<EnvironmentDto> environments) {
        
        this.id = id;
        this.version = version;
        this.name = name;
        this.environments = unmodifiableList(environments);
    }

    public static ProjectDtoBuilder builder() {
        return new ProjectDtoBuilder();
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
    public List<EnvironmentDto> getEnvironments() {
        return environments;
    }
    
    public static final class ProjectDtoBuilder extends AbstractBuilder<ProjectDto> {
        private String id;
        private long version;
        private String name;
        private List<EnvironmentDto> environments;

        private ProjectDtoBuilder() {
        }

        public ProjectDtoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ProjectDtoBuilder version(long version) {
            this.version = version;
            return this;
        }

        public ProjectDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProjectDtoBuilder environments(List<EnvironmentDto> environments) {
            this.environments = environments;
            return this;
        }

        @Override
        protected ProjectDto buildInternal() {
            return new ProjectDto(this);
        }
    }
}
