package com.mercadolibre.fuegodequasar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {
    Double x;
    Double y;

    public Position(double[] positons ){
        this.x = positons[0];
        this.y = positons[1];
    }

    public double[] toArray() {
        double[] pos = {this.x, this.y};
        return pos;
    }
}
