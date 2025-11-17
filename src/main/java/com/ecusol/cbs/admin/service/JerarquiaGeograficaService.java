package com.ecusol.cbs.admin.service;

import com.ecusol.cbs.admin.dto.JerarquiaGeograficaDto;
import com.ecusol.cbs.admin.model.JerarquiaGeografica;
import com.ecusol.cbs.admin.model.Pais;
import com.ecusol.cbs.admin.repository.JerarquiaGeograficaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JerarquiaGeograficaService {

    private final JerarquiaGeograficaRepository repo;

    public JerarquiaGeograficaService(JerarquiaGeograficaRepository repo) {
        this.repo = repo;
    }

    private JerarquiaGeograficaDto toDto(JerarquiaGeografica entity) {
        JerarquiaGeograficaDto dto = new JerarquiaGeograficaDto();
        dto.setJerarquiaId(entity.getJerarquiaId());
        dto.setPaisId(entity.getPais() != null ? entity.getPais().getPaisId() : null);
        dto.setNombreNivel(entity.getNombreNivel());
        dto.setNivel(entity.getNivel());
        return dto;
    }

    private void updateEntityFromDto(JerarquiaGeograficaDto dto, JerarquiaGeografica entity) {
        if (dto.getPaisId() != null) {
            entity.setPais(new Pais(dto.getPaisId()));
        } else {
            entity.setPais(null);
        }
        entity.setNombreNivel(dto.getNombreNivel());
        entity.setNivel(dto.getNivel());
    }

    public List<JerarquiaGeograficaDto> listar() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    public List<JerarquiaGeograficaDto> listarPorPais(Integer paisId) {
        Pais pais = new Pais(paisId);
        return repo.findByPais(pais).stream().map(this::toDto).toList();
    }

    public Optional<JerarquiaGeograficaDto> buscarPorId(Integer jerarquiaId) {
        return repo.findById(jerarquiaId).map(this::toDto);
    }

    public JerarquiaGeograficaDto crear(JerarquiaGeograficaDto dto) {
        JerarquiaGeografica entity = new JerarquiaGeografica();
        updateEntityFromDto(dto, entity);
        JerarquiaGeografica guardada = repo.save(entity);
        return toDto(guardada);
    }

    public Optional<JerarquiaGeograficaDto> actualizar(Integer jerarquiaId, JerarquiaGeograficaDto dto) {
        return repo.findById(jerarquiaId)
                .map(existente -> {
                    updateEntityFromDto(dto, existente);
                    JerarquiaGeografica guardada = repo.save(existente);
                    return toDto(guardada);
                });
    }

    public boolean eliminar(Integer jerarquiaId) {
        if (!repo.existsById(jerarquiaId)) {
            return false;
        }
        repo.deleteById(jerarquiaId);
        return true;
    }
}
