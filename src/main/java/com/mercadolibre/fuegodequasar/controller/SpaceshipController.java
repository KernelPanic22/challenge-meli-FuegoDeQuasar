package com.mercadolibre.fuegodequasar.controller;


import com.mercadolibre.fuegodequasar.dto.SatelliteDTO;
import com.mercadolibre.fuegodequasar.dto.SatelliteSplitDTO;
import com.mercadolibre.fuegodequasar.dto.SpaceshipDTO;
import com.mercadolibre.fuegodequasar.service.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/topsecret_split/{satellite_name}")
    @ResponseBody
    HttpEntity<String> setSatelliteSplit(@RequestBody @Validated SatelliteSplitDTO satellite, @PathVariable(value="satellite_name") String name ){
        return spaceshipService.setSatelliteSplit(satellite,name);
    }

    @GetMapping("/topsecret_split/")
    @ResponseBody
    ResponseEntity<SpaceshipDTO> getSpaceshipSplit(){
        return spaceshipService.getSpaceshipSplit();
    }
}
