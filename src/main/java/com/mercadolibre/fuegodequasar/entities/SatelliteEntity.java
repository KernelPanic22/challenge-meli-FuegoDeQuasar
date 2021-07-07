package com.mercadolibre.fuegodequasar.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "SATELLITES")
@Entity
public class SatelliteEntity implements Serializable {

    private static final long serialVersionUID = 2070766547542608864L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "X")
    private Double x;

    @Column(name = "y")
    private Double y;
}
