package com.mercadolibre.fuegodequasar.dto;


import com.mercadolibre.fuegodequasar.model.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceshipDTO {

    Position position;
    String message;

}
