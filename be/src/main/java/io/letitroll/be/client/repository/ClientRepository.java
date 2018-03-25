package io.letitroll.be.client.repository;


import io.letitroll.be.client.domain.Client;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ClientRepository extends ReactiveCrudRepository<Client, ObjectId> {

    Mono<Client> findByUsername(String username);
}
