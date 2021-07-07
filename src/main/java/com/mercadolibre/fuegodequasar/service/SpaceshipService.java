package com.mercadolibre.fuegodequasar.service;

import com.mercadolibre.fuegodequasar.dto.SatelliteDTO;
import com.mercadolibre.fuegodequasar.dto.SpaceshipDTO;
import org.springframework.http.ResponseEntity;

public interface SpaceshipService {
    ResponseEntity<SpaceshipDTO> getSpaceship(SatelliteDTO satelliteDTO);
}
