package com.ecusol.cbs.admin.service;

import com.ecusol.cbs.admin.dto.EntidadBancariaDto;
import com.ecusol.cbs.admin.model.EntidadBancaria;
import com.ecusol.cbs.admin.repository.EntidadBancariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadBancariaService {

    private final EntidadBancariaRepository repo;

    public EntidadBancariaService(EntidadBancariaRepository repo) {
        this.repo = repo;
    }

    

    private EntidadBancariaDto toDto(EntidadBancaria entity) {
        EntidadBancariaDto dto = new EntidadBancariaDto();
        dto.setEntidadBancariaId(entity.getEntidadBancariaId());
        dto.setRuc(entity.getRuc());
        dto.setRazonSocial(entity.getRazonSocial());
        dto.setNombreComercial(entity.getNombreComercial());
        dto.setSitioWeb(entity.getSitioWeb());
        dto.setEmailContacto(entity.getEmailContacto());
        dto.setCodigoInternacional(entity.getCodigoInternacional());
        return dto;
    }

    private void updateEntityFromDto(EntidadBancariaDto dto, EntidadBancaria entity) {
        entity.setRuc(dto.getRuc());
        entity.setRazonSocial(dto.getRazonSocial());
        entity.setNombreComercial(dto.getNombreComercial());
        entity.setSitioWeb(dto.getSitioWeb());
        entity.setEmailContacto(dto.getEmailContacto());
        entity.setCodigoInternacional(dto.getCodigoInternacional());
    }

   

    public List<EntidadBancariaDto> listar() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<EntidadBancariaDto> buscarPorId(Integer id) {
        return repo.findById(id).map(this::toDto);
    }


    public Optional<EntidadBancariaDto> buscarPorRuc(String ruc) {
        return repo.findByRuc(ruc).map(this::toDto);
    }

    public EntidadBancariaDto crear(EntidadBancariaDto dto) {
        EntidadBancaria entity = new EntidadBancaria();
        updateEntityFromDto(dto, entity);
        EntidadBancaria guardada = repo.save(entity);
        return toDto(guardada);
    }

    public Optional<EntidadBancariaDto> actualizar(Integer id, EntidadBancariaDto dto) {
        return repo.findById(id)
                .map(existente -> {
                    updateEntityFromDto(dto, existente);
                    EntidadBancaria guardada = repo.save(existente);
                    return toDto(guardada);
                });
    }

    public boolean eliminar(Integer id) {
        return repo.findById(id)
                .map(ent -> {
                    repo.delete(ent);
                    return true;
                })
                .orElse(false);
    }
}
