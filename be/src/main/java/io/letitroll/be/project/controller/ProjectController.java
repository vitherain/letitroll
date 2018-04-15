package io.letitroll.be.project.controller;

import io.letitroll.be.api.ApiUrls;
import io.letitroll.be.emitter.repository.ProjectRepository;
import io.letitroll.core.environment.domain.Environment;
import io.letitroll.core.environment.mapper.EnvironmentEntity2DtoMapper;
import io.letitroll.core.environment.repository.EnvironmentRepository;
import io.letitroll.core.project.domain.Project;
import io.letitroll.core.project.dto.ProjectDto;
import io.letitroll.core.project.dto.ProjectDto.ProjectDtoBuilder;
import io.letitroll.core.project.mapper.ProjectEntity2DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@RestController
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectEntity2DtoMapper projectEntity2DtoMapper;
    private final EnvironmentRepository environmentRepository;
    private final EnvironmentEntity2DtoMapper environmentEntity2DtoMapper;

    @Autowired
    public ProjectController(
            final ProjectRepository projectRepository,
            final ProjectEntity2DtoMapper projectEntity2DtoMapper,
            final EnvironmentRepository environmentRepository,
            final EnvironmentEntity2DtoMapper environmentEntity2DtoMapper) {

        this.projectRepository = projectRepository;
        this.projectEntity2DtoMapper = projectEntity2DtoMapper;
        this.environmentRepository = environmentRepository;
        this.environmentEntity2DtoMapper = environmentEntity2DtoMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = ApiUrls.PROJECTS)
    public List<ProjectDto> getProjects() {

        final Iterable<Project> projects = projectRepository.findAll();
        return StreamSupport.stream(projects.spliterator(), false)
                .map(project -> {
                    requireNonNull(project.getId(), "project id must not be null!");

                    final ProjectDtoBuilder builder = projectEntity2DtoMapper.map(project);
                    final List<Environment> envs = environmentRepository.findAllByProjectId(project.getId());
                    builder.environments(envs.stream().map(environmentEntity2DtoMapper::map).collect(toList()));
                    return builder.build();
                })
                .collect(toList());
    }
}
