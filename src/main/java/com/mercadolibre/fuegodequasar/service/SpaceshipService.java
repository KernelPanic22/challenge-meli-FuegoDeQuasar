package com.mercadolibre.fuegodequasar.service;

import com.mercadolibre.fuegodequasar.dto.SatelliteDTO;
import com.mercadolibre.fuegodequasar.dto.SatelliteSplitDTO;
import com.mercadolibre.fuegodequasar.dto.SpaceshipDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface SpaceshipService {
    ResponseEntity<SpaceshipDTO> getSpaceship(SatelliteDTO satelliteDTO);
    HttpEntity<String> setSatelliteSplit(SatelliteSplitDTO satelliteSplitDTO, String name);
    ResponseEntity<SpaceshipDTO> getSpaceshipSplit();
}
