package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {

    // Buscar por nombre
    Optional<Pais> findByNombreIgnoreCase(String nombre);

    // Buscar por ISO 3166-2 (EC, US, MX...)
    Optional<Pais> findByIsoAlpha2(String isoAlpha2);

    // Buscar por ISO 3166-3 (ECU, USA, MEX...)
    Optional<Pais> findByIsoAlpha3(String isoAlpha3);
}
