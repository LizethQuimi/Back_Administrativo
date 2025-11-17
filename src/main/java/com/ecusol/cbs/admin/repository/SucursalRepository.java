package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.Sucursal;
import com.ecusol.cbs.admin.model.EntidadBancaria;
import com.ecusol.cbs.admin.model.LocalizacionGeografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    List<Sucursal> findByEntidadBancaria(EntidadBancaria entidad);

    List<Sucursal> findByLocalizacionGeografica(LocalizacionGeografica loc);

    List<Sucursal> findByActiva(Boolean activa);

    Sucursal findByCodigoUnico(String codigoUnico);
}
