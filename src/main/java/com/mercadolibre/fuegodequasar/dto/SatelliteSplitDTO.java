package com.mercadolibre.fuegodequasar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.fuegodequasar.model.SatelliteSplitModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SatelliteSplitDTO {
    @JsonProperty("satellite")
    SatelliteSplitModel satellite;
}
