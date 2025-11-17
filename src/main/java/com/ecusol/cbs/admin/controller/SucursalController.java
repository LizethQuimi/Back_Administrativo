package com.ecusol.cbs.admin.controller;

import com.ecusol.cbs.admin.dto.SucursalDto;
import com.ecusol.cbs.admin.service.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/sucursales")
@CrossOrigin(origins = "*")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    // GET /api/admin/sucursales
    @GetMapping
    public ResponseEntity<List<SucursalDto>> listarSucursales() {
        return ResponseEntity.ok(sucursalService.listar());
    }

    // GET /api/admin/sucursales/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSucursal(@PathVariable Integer id) {
        return sucursalService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /api/admin/sucursales/entidad/{entidadId}
    @GetMapping("/entidad/{entidadId}")
    public ResponseEntity<List<SucursalDto>> listarPorEntidad(@PathVariable Integer entidadId) {
        return ResponseEntity.ok(sucursalService.listarPorEntidad(entidadId));
    }

    // POST /api/admin/sucursales
    @PostMapping
    public ResponseEntity<SucursalDto> crearSucursal(@RequestBody SucursalDto dto) {
        SucursalDto creada = sucursalService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // PUT /api/admin/sucursales/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarSucursal(@PathVariable Integer id,
                                                @RequestBody SucursalDto dto) {
        return sucursalService.actualizar(id, dto)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /api/admin/sucursales/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id) {
        boolean eliminado = sucursalService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
