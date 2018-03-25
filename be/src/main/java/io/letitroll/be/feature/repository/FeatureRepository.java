package io.letitroll.be.feature.repository;


import io.letitroll.be.feature.domain.Feature;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface FeatureRepository extends ReactiveCrudRepository<Feature, String>, ReactiveSortingRepository<Feature, String> {
}
