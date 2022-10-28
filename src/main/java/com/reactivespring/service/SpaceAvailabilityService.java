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

    /**
     * Saves the data to database
     * @param routeDTOList
     * @return
     */
    public Flux<RouteDTO> addSpaceAvailability(List<RouteDTO> routeDTOList) {
        log.info("Inside addSpaceAvailability() method");
        log.info("Save routeName, vesselName and space status to database {}", routeDTOList);
        return routeDTORepository.saveAll(routeDTOList).log();
    }

    /**
     * gets the data from database after proper filtering based on vesselSize and noOfContainers
     * @param routeDTOList
     * @param noOfContainers
     * @return
     */
    public Flux<RouteDTO> getSpaceAvailability(List<RouteDTO> routeDTOList, Double noOfContainers) {
        log.info("Inside getSpaceAvailability() method");
        log.info("Received routeDTOList :{} , noOfContainers : {}", routeDTOList, noOfContainers);
        List<RouteDTO> routeList = routeDTOList.stream().map(e-> {
            var spaceAvail = e.getVesselSize() >= noOfContainers;
           return new RouteDTO(e.getRouteName(), e.getVesselSize(), spaceAvail);
        }).collect(Collectors.toList());
        log.info("Final Route list with spaceAvailability : {}",routeList);
       return  Flux.fromIterable(routeList).log();
    }

}


// .stream()
//  .filter(e -> e.getVesselSize() >= noOfContainers)