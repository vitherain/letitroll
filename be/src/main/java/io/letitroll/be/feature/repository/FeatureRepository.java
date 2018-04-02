package io.letitroll.be.feature.repository;


import io.letitroll.common.feature.domain.Feature;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;

public interface FeatureRepository extends ReactiveCrudRepository<Feature, ObjectId>, ReactiveSortingRepository<Feature, ObjectId> {

    @Query("{ 'project': ?0 }")
    Flux<Feature> findByProjectId(@NonNull ObjectId projectId, Pageable pageable);
}
