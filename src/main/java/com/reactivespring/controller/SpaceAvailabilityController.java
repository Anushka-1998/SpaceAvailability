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

    @PostMapping("/spaceInfos")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<RouteDTO> addSpaceInfo(@RequestBody List<RouteDTO> routeDTOS)
    {
        log.info("Recieved route Object :{}", routeDTOS);
        return spaceAvailabilityService.addSpaceAvailability(routeDTOS);

    }

    @PostMapping("/getspaceInfos")
    public Flux<RouteDTO> getSpaceInfo(@RequestBody List<RouteDTO> routeDTOList,
                                       @RequestParam("noOfContainers") Double noOfContainers) {
        log.info("Recieved route list request {}", routeDTOList);
        return spaceAvailabilityService.getSpaceAvailability(routeDTOList,noOfContainers);

    }
}
