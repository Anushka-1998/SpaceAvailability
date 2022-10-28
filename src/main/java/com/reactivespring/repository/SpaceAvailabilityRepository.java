package com.reactivespring.repository;


import com.reactivespring.domain.RouteDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@Cacheable
public interface SpaceAvailabilityRepository extends ReactiveMongoRepository<RouteDTO, String> {

}
