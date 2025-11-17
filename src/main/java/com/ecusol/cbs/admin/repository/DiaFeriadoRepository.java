package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.DiaFeriado;
import com.ecusol.cbs.admin.model.Pais;
import com.ecusol.cbs.admin.model.LocalizacionGeografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaFeriadoRepository extends JpaRepository<DiaFeriado, Integer> {

    // Feriados nacionales
    List<DiaFeriado> findByPais(Pais pais);

    // Feriados por ubicación geográfica
    List<DiaFeriado> findByLocalizacionGeografica(LocalizacionGeografica loc);

    // Feriado en una fecha específica
    List<DiaFeriado> findByFecha(LocalDate fecha);

    // Feriado por país + fecha
    List<DiaFeriado> findByPaisAndFecha(Pais pais, LocalDate fecha);
}
