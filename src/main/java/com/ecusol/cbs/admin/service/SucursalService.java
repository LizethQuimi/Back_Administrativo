package com.ecusol.cbs.admin.service;

import com.ecusol.cbs.admin.dto.SucursalDto;
import com.ecusol.cbs.admin.model.EntidadBancaria;
import com.ecusol.cbs.admin.model.LocalizacionGeografica;
import com.ecusol.cbs.admin.model.Sucursal;
import com.ecusol.cbs.admin.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    private SucursalDto toDto(Sucursal entity) {
        SucursalDto dto = new SucursalDto();
        dto.setSucursalId(entity.getSucursalId());
        dto.setLocalizacionGeograficaId(
                entity.getLocalizacionGeografica() != null
                        ? entity.getLocalizacionGeografica().getLocalizacionGeograficaId()
                        : null
        );
        dto.setEntidadBancariaId(
                entity.getEntidadBancaria() != null
                        ? entity.getEntidadBancaria().getEntidadBancariaId()
                        : null
        );
        dto.setCodigoUnico(entity.getCodigoUnico());
        dto.setNombre(entity.getNombre());
        dto.setDireccion(entity.getDireccion());
        dto.setTelefono(entity.getTelefono());
        dto.setLatitud(entity.getLatitud() != null ? entity.getLatitud().doubleValue() : null);
        dto.setLongitud(entity.getLongitud() != null ? entity.getLongitud().doubleValue() : null);
        dto.setActiva(entity.getActiva());
        dto.setFechaCreacion(
                entity.getFechaCreacion() != null ? entity.getFechaCreacion().toString() : null
        );
        return dto;
    }

    private void updateEntityFromDto(SucursalDto dto, Sucursal entity) {
        if (dto.getLocalizacionGeograficaId() != null) {
            entity.setLocalizacionGeografica(
                    new LocalizacionGeografica(dto.getLocalizacionGeograficaId())
            );
        } else {
            entity.setLocalizacionGeografica(null);
        }

        if (dto.getEntidadBancariaId() != null) {
            entity.setEntidadBancaria(
                    new EntidadBancaria(dto.getEntidadBancariaId())
            );
        } else {
            entity.setEntidadBancaria(null);
        }

        entity.setCodigoUnico(dto.getCodigoUnico());
        entity.setNombre(dto.getNombre());
        entity.setDireccion(dto.getDireccion());
        entity.setTelefono(dto.getTelefono());
        entity.setLatitud(dto.getLatitud() != null ? BigDecimal.valueOf(dto.getLatitud()) : null);
        entity.setLongitud(dto.getLongitud() != null ? BigDecimal.valueOf(dto.getLongitud()) : null);
        entity.setActiva(dto.getActiva() != null ? dto.getActiva() : Boolean.TRUE);

        if (entity.getFechaCreacion() == null) {
            entity.setFechaCreacion(LocalDate.now());
        }
    }

    public List<SucursalDto> listar() {
        return sucursalRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<SucursalDto> listarPorEntidad(Integer entidadBancariaId) {
        EntidadBancaria entidad = new EntidadBancaria(entidadBancariaId);
        return sucursalRepository.findByEntidadBancaria(entidad)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<SucursalDto> buscarPorId(Integer sucursalId) {
        return sucursalRepository.findById(sucursalId)
                .map(this::toDto);
    }

    public SucursalDto crear(SucursalDto dto) {
        Sucursal entity = new Sucursal();
        updateEntityFromDto(dto, entity);
        if (entity.getFechaCreacion() == null) {
            entity.setFechaCreacion(LocalDate.now());
        }
        Sucursal guardada = sucursalRepository.save(entity);
        return toDto(guardada);
    }

    public Optional<SucursalDto> actualizar(Integer sucursalId, SucursalDto dto) {
        return sucursalRepository.findById(sucursalId)
                .map(existente -> {
                    updateEntityFromDto(dto, existente);
                    Sucursal guardada = sucursalRepository.save(existente);
                    return toDto(guardada);
                });
    }

    public boolean eliminar(Integer sucursalId) {
        if (!sucursalRepository.existsById(sucursalId)) {
            return false;
        }
        sucursalRepository.deleteById(sucursalId);
        return true;
    }
}
