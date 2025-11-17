package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.JerarquiaGeografica;
import com.ecusol.cbs.admin.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JerarquiaGeograficaRepository extends JpaRepository<JerarquiaGeografica, Integer> {

    // Todas las jerarquías por país
    List<JerarquiaGeografica> findByPais(Pais pais);

    // Filtrar por nivel de jerarquía (1: Provincia, 2: Cantón, 3: Parroquia…)
    List<JerarquiaGeografica> findByNivel(Integer nivel);
}
