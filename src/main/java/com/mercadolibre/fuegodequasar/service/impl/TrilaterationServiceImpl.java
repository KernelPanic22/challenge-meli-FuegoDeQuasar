package com.mercadolibre.fuegodequasar.service.impl;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.mercadolibre.fuegodequasar.model.Position;
import com.mercadolibre.fuegodequasar.service.TrilaterationService;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TrilaterationServiceImpl implements TrilaterationService {
    public Position getSpaceshipLocation(List<Position> positions, double[] distances) {
        double[][] positionArray= new double[positions.size()][];
        AtomicInteger index = new AtomicInteger(-1);
        for (Position currentPosition:
             positions) {
            positionArray[index.incrementAndGet()]= currentPosition.toArray();
        }

        TrilaterationFunction trilateration = new TrilaterationFunction(positionArray, distances);
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(trilateration, new LevenbergMarquardtOptimizer());
        return new Position(solver.solve().getPoint().toArray());
    }
}
