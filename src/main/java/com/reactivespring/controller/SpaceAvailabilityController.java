package com.reactivespring.controller;

import com.reactivespring.domain.RouteDTO;
import com.reactivespring.service.SpaceAvailabilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class SpaceAvailabilityController {
    @Autowired
    private SpaceAvailabilityService spaceAvailabilityService;

    /**
     * This method adds routeDTO to database sends back a flux of routeDTOS
     * @param routeDTOS
     * @return
     */
    @PostMapping("/spaceInfos")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<RouteDTO> addSpaceInfo(@RequestBody List<RouteDTO> routeDTOS)
    {
        log.info("Request received to add routes and spaceDetails {}", routeDTOS);
        return spaceAvailabilityService.addSpaceAvailability(routeDTOS);

    }

    /**
     * This method gets routeDTO from database with their availability status based on noOfContainers
     * @param routeDTOList
     * @param noOfContainers
     * @return
     */
    @PostMapping("/getspaceInfos")
    public Flux<RouteDTO> getSpaceInfo(@RequestBody List<RouteDTO> routeDTOList,
                                       @RequestParam("noOfContainers") Double noOfContainers) {
        log.info("Request received to get routeDTOList: {} for noOfContainers : {} ", routeDTOList,noOfContainers);
        return spaceAvailabilityService.getSpaceAvailability(routeDTOList,noOfContainers);
    }
}
