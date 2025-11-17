package com.ecusol.cbs.admin.service;

import com.ecusol.cbs.admin.dto.EntidadBancariaDto;
import com.ecusol.cbs.admin.model.EntidadBancaria;
import com.ecusol.cbs.admin.repository.EntidadBancariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadBancariaService {

    private final EntidadBancariaRepository entidadRepository;

    public EntidadBancariaService(EntidadBancariaRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
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
        return entidadRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<EntidadBancariaDto> buscarPorId(Integer entidadId) {
        return entidadRepository.findById(entidadId)
                .map(this::toDto);
    }

    public Optional<EntidadBancariaDto> buscarPorRuc(String ruc) {
        return entidadRepository.findByRuc(ruc)
                .map(this::toDto);
    }

    public EntidadBancariaDto crear(EntidadBancariaDto dto) {
        EntidadBancaria entity = new EntidadBancaria();
        updateEntityFromDto(dto, entity);
        EntidadBancaria guardada = entidadRepository.save(entity);
        return toDto(guardada);
    }

    public Optional<EntidadBancariaDto> actualizar(Integer entidadId, EntidadBancariaDto dto) {
        return entidadRepository.findById(entidadId)
                .map(existente -> {
                    updateEntityFromDto(dto, existente);
                    EntidadBancaria guardada = entidadRepository.save(existente);
                    return toDto(guardada);
                });
    }

    public boolean eliminar(Integer entidadId) {
        if (!entidadRepository.existsById(entidadId)) {
            return false;
        }
        entidadRepository.deleteById(entidadId);
        return true;
    }
}
