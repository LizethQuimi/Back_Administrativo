package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.Sucursal;
import com.ecusol.cbs.admin.model.EntidadBancaria;
import com.ecusol.cbs.admin.model.LocalizacionGeografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    // Buscar sucursales por entidad bancaria
    List<Sucursal> findByEntidadBancaria(EntidadBancaria entidad);

    // Buscar por ubicación (provincia/cantón/parroquia)
    List<Sucursal> findByLocalizacionGeografica(LocalizacionGeografica loc);

    // Buscar por estado
    List<Sucursal> findByActiva(Boolean activa);

    // Buscar por código único
    Sucursal findByCodigoUnico(String codigoUnico);
}
