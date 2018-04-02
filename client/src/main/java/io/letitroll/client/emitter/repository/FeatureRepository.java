package io.letitroll.client.emitter.repository;

import io.letitroll.common.feature.domain.Feature;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface FeatureRepository extends CrudRepository<Feature, ObjectId> {
}
