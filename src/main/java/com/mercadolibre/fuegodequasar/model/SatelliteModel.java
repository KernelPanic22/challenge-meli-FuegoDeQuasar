package com.mercadolibre.fuegodequasar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SatelliteModel {
    @JsonProperty("name")
    String name;
    @JsonProperty("distance")
    double distance;
    @JsonProperty
    Position position;
    @JsonProperty("message")
    List<String> message;
}
