package io.letitroll.core.targeting.repository;


import io.letitroll.core.targeting.domain.FeatureTargeting;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;

public interface FeatureTargetingRepository extends PagingAndSortingRepository<FeatureTargeting, ObjectId> {

    @Query("{ 'environment': ?0 }")
    Page<FeatureTargeting> findAllByEnvironmentId(@NonNull ObjectId environmentId, @NonNull Pageable pageable);
}
