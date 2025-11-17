package com.ecusol.cbs.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Sucursales", schema = "administrativos")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sucursalId")
    private Integer sucursalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacionGeograficaId", nullable = false)
    private LocalizacionGeografica localizacionGeografica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidadBancariaId", nullable = false)
    private EntidadBancaria entidadBancaria;

    @Column(name = "codigoUnico", nullable = false, length = 20)
    private String codigoUnico;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 250)
    private String direccion;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "latitud", precision = 10, scale = 7)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 10, scale = 7)
    private BigDecimal longitud;

    @Column(name = "activa", nullable = false)
    private Boolean activa;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion;

    public Sucursal(Integer sucursalId) {
        this.sucursalId = sucursalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sucursal sucursal = (Sucursal) o;
        return Objects.equals(sucursalId, sucursal.sucursalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sucursalId);
    }
}
