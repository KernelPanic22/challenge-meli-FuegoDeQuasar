package com.mercadolibre.fuegodequasar.service.impl;

import com.mercadolibre.fuegodequasar.dto.SatelliteDTO;
import com.mercadolibre.fuegodequasar.dto.SatelliteSplitDTO;
import com.mercadolibre.fuegodequasar.dto.SpaceshipDTO;
import com.mercadolibre.fuegodequasar.entities.SatelliteEntity;
import com.mercadolibre.fuegodequasar.model.Position;
import com.mercadolibre.fuegodequasar.model.SatelliteModel;
import com.mercadolibre.fuegodequasar.model.SatelliteSplitModel;
import com.mercadolibre.fuegodequasar.repository.SatelliteRepository;
import com.mercadolibre.fuegodequasar.service.MessageService;
import com.mercadolibre.fuegodequasar.service.SpaceshipService;
import com.mercadolibre.fuegodequasar.service.TrilaterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {

    @Value("${spaceship.minimum.satellites}")
    Integer spaceshipMinimumSatellites;

    @Value("${jedis.pool.url}")
    String jedisPoolUrl;

    @Autowired
    private SatelliteRepository satelliteRepository;

    @Autowired
    private TrilaterationService trilaterationService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RedisTemplate<String, SatelliteSplitDTO> redisTemplate;

    public HttpEntity<String> setSatelliteSplit(SatelliteSplitDTO satelliteSplitDTO, String name){
        this.redisTemplate.opsForValue().set("satellite:"+ name,satelliteSplitDTO);
        return ResponseEntity.ok("Parametro agregado correctamente");
    }

    public ResponseEntity<SpaceshipDTO> getSpaceshipSplit(){
        SatelliteDTO satelliteDTO = new SatelliteDTO();
        List<SatelliteModel> satellites = new ArrayList<>();
        Set<String> redisKeys = this.redisTemplate.keys("satellite:*");
        // Store the keys in a List
        List<String> keysList = new ArrayList<>();
        for (String currentKey:
                redisKeys) {
            SatelliteSplitDTO currentSatelliteDto=this.redisTemplate.opsForValue().get(currentKey);
            SatelliteSplitModel currentSatelliteModel = currentSatelliteDto.getSatellite();
            String satelliteName = currentKey.replace("satellite:","");
            satellites.add(SatelliteModel.builder().distance(currentSatelliteModel.getDistance())
                                    .message(currentSatelliteModel.getMessage())
                                    .name(satelliteName)
                                    .build());
        }
        this.redisTemplate.delete(redisKeys);
        satelliteDTO.setSatellites(satellites);
        return this.getSpaceship(satelliteDTO);
    }




    public ResponseEntity<SpaceshipDTO> getSpaceship(SatelliteDTO satelliteDTO){
        try{
        if(satelliteDTO.getSatellites().size() < spaceshipMinimumSatellites){
                throw new Exception("Spaceship Minimum Satellites is "+spaceshipMinimumSatellites);
            }
        satelliteDTO.setPositions(getSatellites(satelliteDTO));
        Position position = trilaterationService.getSpaceshipLocation(satelliteDTO.getPositions(),satelliteDTO.getDistances());
        String message = messageService.decodeMessages(satelliteDTO.getMessages());

        return new ResponseEntity<>(SpaceshipDTO.builder().position(position).message(message).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(SpaceshipDTO.builder().position(
                    Position.builder().build())
                    .message(e.getMessage())
                    .build()
                    ,HttpStatus.BAD_REQUEST);
        }
    }

    private List<SatelliteEntity> getSatellites(SatelliteDTO satelliteDTO) throws Exception {
        List<SatelliteModel> satellites = satelliteDTO.getSatellites();
        List<SatelliteEntity> satellitesFromDb = new ArrayList<>();
        for(SatelliteModel satellite : satellites) {
            SatelliteEntity satelliteFromDb = satelliteRepository.findByName(satellite.getName().toUpperCase());
            if(satelliteFromDb == null)
                throw new Exception("No se encuentra uno o mas satelites en la base de datos");
        satellitesFromDb.add(satelliteFromDb);
        }
        return satellitesFromDb;
    }
}
