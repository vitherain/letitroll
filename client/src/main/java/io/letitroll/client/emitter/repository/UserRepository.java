package io.letitroll.client.emitter.repository;

import io.letitroll.common.user.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, ObjectId> {
}
