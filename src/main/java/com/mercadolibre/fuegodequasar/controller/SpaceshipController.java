package com.mercadolibre.fuegodequasar.controller;


import com.mercadolibre.fuegodequasar.dto.SatelliteDTO;
import com.mercadolibre.fuegodequasar.dto.SpaceshipDTO;
import com.mercadolibre.fuegodequasar.service.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;

    @PostMapping("/topsecret/")
    @ResponseBody
    ResponseEntity<SpaceshipDTO> getSpaceShip(@RequestBody @Validated SatelliteDTO satellites){
        return spaceshipService.getSpaceship(satellites);
    }
}
