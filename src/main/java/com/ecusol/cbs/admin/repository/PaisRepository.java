package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, Integer> {

    
    Optional<Pais> findByNombreIgnoreCase(String nombre);

    
    Optional<Pais> findByIsoAlpha2(String isoAlpha2);
}
