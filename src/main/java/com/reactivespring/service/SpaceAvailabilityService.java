package com.reactivespring.service;

import com.reactivespring.domain.RouteDTO;
import com.reactivespring.repository.SpaceAvailabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SpaceAvailabilityService {
    @Autowired
    private SpaceAvailabilityRepository routeDTORepository;



    public Flux<RouteDTO> addSpaceAvailability(List<RouteDTO> routeDTOList) {
        log.info("Received Route Object :{}", routeDTOList);
        return routeDTORepository.saveAll(routeDTOList).log();
    }

    public Flux<RouteDTO> getSpaceAvailability(List<RouteDTO> routeDTOList, Double noOfContainers) {
        log.info("Received Route Object :{} , No of containers : {}", routeDTOList, noOfContainers);
        List<RouteDTO> routeList = routeDTOList
                .stream()
                .filter(e -> e.getVesselSize() >= noOfContainers)
                .collect(Collectors.toList());
        log.info("routeList : {}",routeList);
       return  Flux.fromIterable(routeList).log();
    }

}
