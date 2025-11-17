package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.EntidadBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadBancariaRepository extends JpaRepository<EntidadBancaria, Integer> {

    // Buscar por RUC
    Optional<EntidadBancaria> findByRuc(String ruc);

    // Buscar por razón social
    Optional<EntidadBancaria> findByRazonSocialIgnoreCase(String razonSocial);

    // Buscar por nombre comercial
    Optional<EntidadBancaria> findByNombreComercialIgnoreCase(String nombreComercial);

    // Buscar por SWIFT / Código Internacional
    Optional<EntidadBancaria> findByCodigoInternacional(String codigoInternacional);
}
