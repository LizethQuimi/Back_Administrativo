package com.ecusol.cbs.admin.repository;

import com.ecusol.cbs.admin.model.EntidadBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntidadBancariaRepository extends JpaRepository<EntidadBancaria, Integer> {


    Optional<EntidadBancaria> findByRuc(String ruc);
}
