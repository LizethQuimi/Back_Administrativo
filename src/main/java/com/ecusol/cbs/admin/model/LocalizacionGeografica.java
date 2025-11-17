package com.ecusol.cbs.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "LocalizacionesGeograficas", schema = "administrativos")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LocalizacionGeografica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "localizacionGeograficaId")
    private Integer localizacionGeograficaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jerarquiaId", nullable = false)
    private JerarquiaGeografica jerarquia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentLocalizacionGeograficaId")
    private LocalizacionGeografica parent;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "codigo", length = 30)
    private String codigo;

    public LocalizacionGeografica(Integer localizacionGeograficaId) {
        this.localizacionGeograficaId = localizacionGeograficaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalizacionGeografica that = (LocalizacionGeografica) o;
        return Objects.equals(localizacionGeograficaId, that.localizacionGeograficaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localizacionGeograficaId);
    }
}
