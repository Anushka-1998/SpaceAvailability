package com.reactivespring.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RouteDTO implements Serializable {

    @JsonProperty("routeName")
    private String routeName;
    @JsonProperty("vesselSize")
    private double vesselSize;


}


