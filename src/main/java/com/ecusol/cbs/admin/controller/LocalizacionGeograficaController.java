package com.ecusol.cbs.admin.controller;

import com.ecusol.cbs.admin.dto.LocalizacionGeograficaDto;
import com.ecusol.cbs.admin.service.LocalizacionGeograficaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/localizaciones")
@CrossOrigin(origins = "*")
public class LocalizacionGeograficaController {

    private final LocalizacionGeograficaService locService;

    public LocalizacionGeograficaController(LocalizacionGeograficaService locService) {
        this.locService = locService;
    }

    // GET /api/admin/localizaciones
    @GetMapping
    public ResponseEntity<List<LocalizacionGeograficaDto>> listarLocalizaciones() {
        return ResponseEntity.ok(locService.listar());
    }

    // GET /api/admin/localizaciones/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerLocalizacion(@PathVariable Integer id) {
        return locService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /api/admin/localizaciones/jerarquia/{jerarquiaId}
    @GetMapping("/jerarquia/{jerarquiaId}")
    public ResponseEntity<List<LocalizacionGeograficaDto>> listarPorJerarquia(@PathVariable Integer jerarquiaId) {
        return ResponseEntity.ok(locService.listarPorJerarquia(jerarquiaId));
    }

    // POST /api/admin/localizaciones
    @PostMapping
    public ResponseEntity<LocalizacionGeograficaDto> crearLocalizacion(@RequestBody LocalizacionGeograficaDto dto) {
        LocalizacionGeograficaDto creada = locService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // PUT /api/admin/localizaciones/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLocalizacion(@PathVariable Integer id,
                                                    @RequestBody LocalizacionGeograficaDto dto) {
        return locService.actualizar(id, dto)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /api/admin/localizaciones/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLocalizacion(@PathVariable Integer id) {
        boolean eliminado = locService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
