package io.letitroll.core.project.mapper;

import io.letitroll.core.project.domain.Project;
import io.letitroll.core.project.dto.ProjectDto;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
public class LaboriousProjectEntity2DtoMapper implements ProjectEntity2DtoMapper {

    @Override
    public ProjectDto.ProjectDtoBuilder map(@NonNull final Project source) {
        requireNonNull(source, "source must not be null!");
        final String id = Optional.ofNullable(source.getId())
                .map(ObjectId::toString)
                .orElse(null);
        requireNonNull(id, "project id must not be null!");
        return ProjectDto.builder()
                .id(id)
                .version(source.getVersion())
                .name(source.getName());
    }
}
