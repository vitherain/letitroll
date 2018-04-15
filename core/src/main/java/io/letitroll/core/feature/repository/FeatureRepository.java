package io.letitroll.core.feature.repository;


import io.letitroll.core.feature.domain.Feature;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;

public interface FeatureRepository extends PagingAndSortingRepository<Feature, ObjectId> {

    @Query("{ 'project': ?0 }")
    Page<Feature> findAllByProjectId(@NonNull ObjectId projectId, @NonNull Pageable pageable);
}
