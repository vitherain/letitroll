package io.letitroll.be.feature.repository;


import io.letitroll.common.feature.domain.Feature;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface FeatureRepository extends ReactiveCrudRepository<Feature, ObjectId>, ReactiveSortingRepository<Feature, ObjectId> {

    Flux<Feature> findByName(String name, Pageable pageable);
}
