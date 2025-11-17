package com.ecusol.cbs.admin.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "EntidadesBancarias", schema = "administrativos")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EntidadBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entidadBancariaId")
    private Integer entidadBancariaId;

    @Column(name = "ruc", nullable = false, length = 20)
    private String ruc;

    @Column(name = "razonSocial", nullable = false, length = 200)
    private String razonSocial;

    @Column(name = "nombreComercial", length = 200)
    private String nombreComercial;

    @Column(name = "sitioWeb", length = 200)
    private String sitioWeb;

    @Column(name = "emailContacto", length = 120)
    private String emailContacto;

    @Column(name = "codigoInternacional", length = 20)
    private String codigoInternacional; // Código SWIFT

    public EntidadBancaria(Integer entidadBancariaId) {
        this.entidadBancariaId = entidadBancariaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadBancaria that = (EntidadBancaria) o;
        return Objects.equals(entidadBancariaId, that.entidadBancariaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entidadBancariaId);
    }
}

