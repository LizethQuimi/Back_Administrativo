package com.ecusol.cbs.admin.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "DiasFeriados", schema = "administrativos")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaFeriado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feriadoId")
    private Integer feriadoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paisId", nullable = false)
    private Pais pais;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacionGeograficaId")
    private LocalizacionGeografica localizacionGeografica;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "esFinDeSemana", nullable = false)
    private Boolean esFinDeSemana;

    public DiaFeriado(Integer feriadoId) {
        this.feriadoId = feriadoId;
    }
}
