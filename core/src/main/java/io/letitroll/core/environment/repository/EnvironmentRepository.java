package io.letitroll.core.environment.repository;


import io.letitroll.core.environment.domain.Environment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface EnvironmentRepository extends PagingAndSortingRepository<Environment, ObjectId> {

    @Query("{ 'project': ?0 }")
    List<Environment> findAllByProjectId(@NonNull ObjectId projectId);
}
