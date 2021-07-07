package com.mercadolibre.fuegodequasar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.fuegodequasar.entities.SatelliteEntity;
import com.mercadolibre.fuegodequasar.model.Position;
import com.mercadolibre.fuegodequasar.model.SatelliteModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SatelliteDTO {
    @JsonProperty("satellites")
    List<SatelliteModel> satellites;

    public double[] getDistances(){
        return this.satellites.stream().map(
                SatelliteModel::getDistance
        ).collect(Collectors.toList())
                .stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    public List<List<String>> getMessages(){
        return this.satellites.stream().map(
                SatelliteModel::getMessage
        ).collect(Collectors.toList());
    }

    public void setPositions(List<SatelliteEntity> satellitesWithPosition) {
        AtomicInteger index = new AtomicInteger();
        this.satellites.sort(Comparator.comparing(SatelliteModel::getName));
        satellitesWithPosition.sort(Comparator.comparing(SatelliteEntity::getName));

        for (SatelliteModel satelliteModel :
                satellites) {
            SatelliteEntity currentSatellite = satellitesWithPosition.get(index.get());
            satelliteModel.setPosition(new Position(currentSatellite.getX(), currentSatellite.getY()));
            index.incrementAndGet();
        }
    }

    public List<Position> getPositions(){
        return this.satellites.stream().map(SatelliteModel::getPosition).collect(Collectors.toList());
        }
    }

