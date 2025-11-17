package com.ecusol.cbs.admin.service;

import com.ecusol.cbs.admin.dto.DiaFeriadoDto;
import com.ecusol.cbs.admin.model.DiaFeriado;
import com.ecusol.cbs.admin.model.LocalizacionGeografica;
import com.ecusol.cbs.admin.model.Pais;
import com.ecusol.cbs.admin.repository.DiaFeriadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DiaFeriadoService {

    private final DiaFeriadoRepository repo;

    public DiaFeriadoService(DiaFeriadoRepository repo) {
        this.repo = repo;
    }

    private DiaFeriadoDto toDto(DiaFeriado entity) {
        DiaFeriadoDto dto = new DiaFeriadoDto();
        dto.setFeriadoId(entity.getFeriadoId());
        dto.setPaisId(entity.getPais() != null ? entity.getPais().getPaisId() : null);
        dto.setLocalizacionGeograficaId(
                entity.getLocalizacionGeografica() != null
                        ? entity.getLocalizacionGeografica().getLocalizacionGeograficaId()
                        : null
        );
        dto.setFecha(entity.getFecha());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEsFinDeSemana(entity.getEsFinDeSemana());
        return dto;
    }

    private void updateEntityFromDto(DiaFeriadoDto dto, DiaFeriado entity) {
        if (dto.getPaisId() != null) {
            entity.setPais(new Pais(dto.getPaisId()));
        } else {
            entity.setPais(null);
        }

        if (dto.getLocalizacionGeograficaId() != null) {
            entity.setLocalizacionGeografica(
                    new LocalizacionGeografica(dto.getLocalizacionGeograficaId())
            );
        } else {
            entity.setLocalizacionGeografica(null);
        }

        entity.setFecha(dto.getFecha());
        entity.setDescripcion(dto.getDescripcion());
        entity.setEsFinDeSemana(
                dto.getEsFinDeSemana() != null ? dto.getEsFinDeSemana() : Boolean.FALSE
        );
    }

    public List<DiaFeriadoDto> listar() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    public List<DiaFeriadoDto> listarPorPais(Integer paisId) {
        Pais pais = new Pais(paisId);
        return repo.findByPais(pais).stream().map(this::toDto).toList();
    }

    public List<DiaFeriadoDto> listarPorPaisYFecha(Integer paisId, LocalDate fecha) {
        Pais pais = new Pais(paisId);
        return repo.findByPaisAndFecha(pais, fecha).stream().map(this::toDto).toList();
    }

    public Optional<DiaFeriadoDto> buscarPorId(Integer feriadoId) {
        return repo.findById(feriadoId).map(this::toDto);
    }

    public DiaFeriadoDto crear(DiaFeriadoDto dto) {
        DiaFeriado entity = new DiaFeriado();
        updateEntityFromDto(dto, entity);
        DiaFeriado guardado = repo.save(entity);
        return toDto(guardado);
    }

    public Optional<DiaFeriadoDto> actualizar(Integer feriadoId, DiaFeriadoDto dto) {
        return repo.findById(feriadoId)
                .map(existente -> {
                    updateEntityFromDto(dto, existente);
                    DiaFeriado guardado = repo.save(existente);
                    return toDto(guardado);
                });
    }

    public boolean eliminar(Integer feriadoId) {
        if (!repo.existsById(feriadoId)) {
            return false;
        }
        repo.deleteById(feriadoId);
        return true;
    }
}
