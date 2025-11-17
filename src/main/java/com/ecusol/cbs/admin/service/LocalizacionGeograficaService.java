package com.ecusol.cbs.admin.service;

import com.ecusol.cbs.admin.dto.LocalizacionGeograficaDto;
import com.ecusol.cbs.admin.model.JerarquiaGeografica;
import com.ecusol.cbs.admin.model.LocalizacionGeografica;
import com.ecusol.cbs.admin.repository.LocalizacionGeograficaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalizacionGeograficaService {

    private final LocalizacionGeograficaRepository repo;

    public LocalizacionGeograficaService(LocalizacionGeograficaRepository repo) {
        this.repo = repo;
    }

    private LocalizacionGeograficaDto toDto(LocalizacionGeografica entity) {
        LocalizacionGeograficaDto dto = new LocalizacionGeograficaDto();
        dto.setLocalizacionGeograficaId(entity.getLocalizacionGeograficaId());
        dto.setJerarquiaId(
                entity.getJerarquia() != null ? entity.getJerarquia().getJerarquiaId() : null
        );
        dto.setParentLocalizacionGeograficaId(
                entity.getParent() != null ? entity.getParent().getLocalizacionGeograficaId() : null
        );
        dto.setNombre(entity.getNombre());
        dto.setCodigo(entity.getCodigo());
        return dto;
    }

    private void updateEntityFromDto(LocalizacionGeograficaDto dto, LocalizacionGeografica entity) {
        if (dto.getJerarquiaId() != null) {
            entity.setJerarquia(new JerarquiaGeografica(dto.getJerarquiaId()));
        } else {
            entity.setJerarquia(null);
        }

        if (dto.getParentLocalizacionGeograficaId() != null) {
            entity.setParent(new LocalizacionGeografica(dto.getParentLocalizacionGeograficaId()));
        } else {
            entity.setParent(null);
        }

        entity.setNombre(dto.getNombre());
        entity.setCodigo(dto.getCodigo());
    }

    public List<LocalizacionGeograficaDto> listar() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    public List<LocalizacionGeograficaDto> listarPorJerarquia(Integer jerarquiaId) {
        JerarquiaGeografica jerarquia = new JerarquiaGeografica(jerarquiaId);
        return repo.findByJerarquia(jerarquia).stream().map(this::toDto).toList();
    }

    public Optional<LocalizacionGeograficaDto> buscarPorId(Integer localizacionId) {
        return repo.findById(localizacionId).map(this::toDto);
    }

    public LocalizacionGeograficaDto crear(LocalizacionGeograficaDto dto) {
        LocalizacionGeografica entity = new LocalizacionGeografica();
        updateEntityFromDto(dto, entity);
        LocalizacionGeografica guardada = repo.save(entity);
        return toDto(guardada);
    }

    public Optional<LocalizacionGeograficaDto> actualizar(Integer localizacionId, LocalizacionGeograficaDto dto) {
        return repo.findById(localizacionId)
                .map(existente -> {
                    updateEntityFromDto(dto, existente);
                    LocalizacionGeografica guardada = repo.save(existente);
                    return toDto(guardada);
                });
    }

    public boolean eliminar(Integer localizacionId) {
        if (!repo.existsById(localizacionId)) {
            return false;
        }
        repo.deleteById(localizacionId);
        return true;
    }
}
