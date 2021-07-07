package com.mercadolibre.fuegodequasar.repository;

import com.mercadolibre.fuegodequasar.entities.SatelliteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelliteRepository extends JpaRepository<SatelliteEntity, Integer> {
    SatelliteEntity findByName(String name);
}
