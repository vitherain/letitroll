package io.letitroll.be.user.repository;


import io.letitroll.be.user.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, ObjectId> {

    Mono<User> findByUsername(String username);
}
