package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.LocalizacionGeografica;
import com.ecusol.cbs.admin.model.JerarquiaGeografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizacionGeograficaRepository extends JpaRepository<LocalizacionGeografica, Integer> {

    List<LocalizacionGeografica> findByJerarquia(JerarquiaGeografica jerarquia);

    List<LocalizacionGeografica> findByParent(LocalizacionGeografica parent);

    List<LocalizacionGeografica> findByNombreContainingIgnoreCase(String nombre);
}
