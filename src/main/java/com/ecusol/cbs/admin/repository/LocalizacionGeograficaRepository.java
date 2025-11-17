package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.LocalizacionGeografica;
import com.ecusol.cbs.admin.model.JerarquiaGeografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizacionGeograficaRepository extends JpaRepository<LocalizacionGeografica, Integer> {

    // Todas las ubicaciones por jerarquía
    List<LocalizacionGeografica> findByJerarquia(JerarquiaGeografica jerarquia);

    // Buscar hijos de una localización
    List<LocalizacionGeografica> findByParent(LocalizacionGeografica parent);

    // Buscar por nombre
    List<LocalizacionGeografica> findByNombreContainingIgnoreCase(String nombre);
}
