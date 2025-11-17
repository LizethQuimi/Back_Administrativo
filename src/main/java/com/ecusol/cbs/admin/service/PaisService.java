package com.ecusol.cbs.admin.service;

import com.ecusol.cbs.admin.dto.PaisDto;
import com.ecusol.cbs.admin.model.Pais;
import com.ecusol.cbs.admin.repository.PaisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    private final PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    // ---------- Mappers ----------

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

    // ---------- Métodos públicos ----------

    public List<PaisDto> listar() {
        return paisRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<PaisDto> buscarPorId(Integer paisId) {
        return paisRepository.findById(paisId)
                .map(this::toDto);
    }

    public Optional<PaisDto> buscarPorNombre(String nombre) {
        return paisRepository.findByNombreIgnoreCase(nombre)
                .map(this::toDto);
    }

    public Optional<PaisDto> buscarPorIsoAlpha2(String isoAlpha2) {
        return paisRepository.findByIsoAlpha2(isoAlpha2)
                .map(this::toDto);
    }

    public PaisDto crear(PaisDto dto) {
        Pais entity = new Pais();
        updateEntityFromDto(dto, entity);
        Pais guardado = paisRepository.save(entity);
        return toDto(guardado);
    }

    public Optional<PaisDto> actualizar(Integer paisId, PaisDto dto) {
        return paisRepository.findById(paisId)
                .map(existente -> {
                    updateEntityFromDto(dto, existente);
                    Pais guardado = paisRepository.save(existente);
                    return toDto(guardado);
                });
    }

    public boolean eliminar(Integer paisId) {
        if (!paisRepository.existsById(paisId)) {
            return false;
        }
        paisRepository.deleteById(paisId);
        return true;
    }
}
