package io.letitroll.be.feature.repository;


import io.letitroll.common.feature.domain.Feature;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface FeatureRepository extends ReactiveCrudRepository<Feature, ObjectId>, ReactiveSortingRepository<Feature, ObjectId> {
}
