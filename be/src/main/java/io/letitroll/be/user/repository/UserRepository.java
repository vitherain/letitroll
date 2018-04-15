package io.letitroll.be.user.repository;


import io.letitroll.common.user.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends CrudRepository<User, ObjectId> {

    User findByUsername(@NonNull String username);
}
