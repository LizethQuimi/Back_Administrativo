package com.ecusol.cbs.admin.service;

import com.ecusol.cbs.admin.dto.PaisDto;
import com.ecusol.cbs.admin.model.Pais;
import com.ecusol.cbs.admin.repository.PaisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    private final PaisRepository repo;

    public PaisService(PaisRepository repo) {
        this.repo = repo;
    }

    private PaisDto toDto(Pais entity) {
        PaisDto dto = new PaisDto();
        dto.setPaisId(entity.getPaisId());
        dto.setNombre(entity.getNombre());
        dto.setIsoAlpha2(entity.getIsoAlpha2());
        dto.setIsoAlpha3(entity.getIsoAlpha3());
        dto.setCodigoNumerico(entity.getCodigoNumerico());
        return dto;
    }

    private void updateEntityFromDto(PaisDto dto, Pais entity) {
        entity.setNombre(dto.getNombre());
        entity.setIsoAlpha2(dto.getIsoAlpha2());
        entity.setIsoAlpha3(dto.getIsoAlpha3());
        entity.setCodigoNumerico(dto.getCodigoNumerico());
    }

    public List<PaisDto> listar() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<PaisDto> buscarPorId(Integer paisId) {
        return repo.findById(paisId).map(this::toDto);
    }

    public PaisDto crear(PaisDto dto) {
        Pais entity = new Pais();
        updateEntityFromDto(dto, entity);
        Pais guardado = repo.save(entity);
        return toDto(guardado);
    }

    public Optional<PaisDto> actualizar(Integer paisId, PaisDto dto) {
        return repo.findById(paisId)
                .map(existente -> {
                    updateEntityFromDto(dto, existente);
                    Pais guardado = repo.save(existente);
                    return toDto(guardado);
                });
    }

    public boolean eliminar(Integer paisId) {
        if (!repo.existsById(paisId)) {
            return false;
        }
        repo.deleteById(paisId);
        return true;
    }
}
