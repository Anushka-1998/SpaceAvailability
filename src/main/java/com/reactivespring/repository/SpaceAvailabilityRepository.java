package com.reactivespring.repository;


import com.reactivespring.domain.RouteDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SpaceAvailabilityRepository extends ReactiveMongoRepository<RouteDTO, String> {

}
