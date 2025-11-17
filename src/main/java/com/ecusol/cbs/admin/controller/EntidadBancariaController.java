package com.ecusol.cbs.admin.controller;

import com.ecusol.cbs.admin.dto.EntidadBancariaDto;
import com.ecusol.cbs.admin.service.EntidadBancariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/entidades-bancarias")
@CrossOrigin(origins = "*")
public class EntidadBancariaController {

    private final EntidadBancariaService entidadService;

    public EntidadBancariaController(EntidadBancariaService entidadService) {
        this.entidadService = entidadService;
    }

    // GET /api/admin/entidades-bancarias
    @GetMapping
    public ResponseEntity<List<EntidadBancariaDto>> listarEntidades() {
        return ResponseEntity.ok(entidadService.listar());
    }

    // GET /api/admin/entidades-bancarias/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEntidad(@PathVariable Integer id) {
        return entidadService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /api/admin/entidades-bancarias/ruc/{ruc}
    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<?> obtenerPorRuc(@PathVariable String ruc) {
        return entidadService.buscarPorRuc(ruc)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/admin/entidades-bancarias
    @PostMapping
    public ResponseEntity<EntidadBancariaDto> crearEntidad(@RequestBody EntidadBancariaDto dto) {
        EntidadBancariaDto creada = entidadService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // PUT /api/admin/entidades-bancarias/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEntidad(@PathVariable Integer id,
                                               @RequestBody EntidadBancariaDto dto) {
        return entidadService.actualizar(id, dto)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /api/admin/entidades-bancarias/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntidad(@PathVariable Integer id) {
        boolean eliminado = entidadService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
