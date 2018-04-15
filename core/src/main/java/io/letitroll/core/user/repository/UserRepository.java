package io.letitroll.core.user.repository;

import io.letitroll.core.user.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends CrudRepository<User, ObjectId> {

    User findByUsername(@NonNull String username);
}
