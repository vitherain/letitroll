package io.letitroll.be.emitter.repository;

import io.letitroll.common.project.domain.Project;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, ObjectId> {
}
