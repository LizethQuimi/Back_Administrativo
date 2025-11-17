package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.JerarquiaGeografica;
import com.ecusol.cbs.admin.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JerarquiaGeograficaRepository extends JpaRepository<JerarquiaGeografica, Integer> {

    List<JerarquiaGeografica> findByPais(Pais pais);

    List<JerarquiaGeografica> findByNivel(Integer nivel);
}
