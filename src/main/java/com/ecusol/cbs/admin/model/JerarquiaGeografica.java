package com.ecusol.cbs.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "JerarquiasGeograficas", schema = "administrativos")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JerarquiaGeografica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jerarquiaId")
    private Integer jerarquiaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paisId", nullable = false)
    private Pais pais;

    @Column(name = "nombreNivel", nullable = false, length = 50)
    private String nombreNivel;

    @Column(name = "nivel", nullable = false)
    private Integer nivel;

    public JerarquiaGeografica(Integer jerarquiaId) {
        this.jerarquiaId = jerarquiaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JerarquiaGeografica that = (JerarquiaGeografica) o;
        return Objects.equals(jerarquiaId, that.jerarquiaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jerarquiaId);
    }
}
