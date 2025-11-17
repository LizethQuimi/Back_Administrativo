package com.ecusol.cbs.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Paises", schema = "administrativos")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paisId")
    private Integer paisId;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "isoAlpha2", nullable = false, length = 2)
    private String isoAlpha2;

    @Column(name = "isoAlpha3", length = 3)
    private String isoAlpha3;

    @Column(name = "codigoNumerico", length = 4)
    private String codigoNumerico;

    // Constructor solo con la PK (lo usa el service: new Pais(id))
    public Pais(Integer paisId) {
        this.paisId = paisId;
    }
}
