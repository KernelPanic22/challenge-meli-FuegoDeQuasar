package com.mercadolibre.fuegodequasar.service;

import com.mercadolibre.fuegodequasar.model.Position;

import java.util.List;

public interface TrilaterationService {
    Position getSpaceshipLocation(List<Position> positions, double[] distances);
}
