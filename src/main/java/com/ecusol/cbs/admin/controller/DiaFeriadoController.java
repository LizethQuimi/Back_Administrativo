package com.ecusol.cbs.admin.controller;

import com.ecusol.cbs.admin.dto.DiaFeriadoDto;
import com.ecusol.cbs.admin.service.DiaFeriadoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/feriados")
@CrossOrigin(origins = "*")
public class DiaFeriadoController {

    private final DiaFeriadoService feriadoService;

    public DiaFeriadoController(DiaFeriadoService feriadoService) {
        this.feriadoService = feriadoService;
    }

    @GetMapping
    public ResponseEntity<List<DiaFeriadoDto>> listarFeriados() {
        return ResponseEntity.ok(feriadoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerFeriado(@PathVariable Integer id) {
        return feriadoService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pais/{paisId}")
    public ResponseEntity<List<DiaFeriadoDto>> listarPorPais(@PathVariable Integer paisId) {
        return ResponseEntity.ok(feriadoService.listarPorPais(paisId));
    }

    @GetMapping("/pais/{paisId}/fecha")
    public ResponseEntity<List<DiaFeriadoDto>> listarPorPaisYFecha(
            @PathVariable Integer paisId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        return ResponseEntity.ok(feriadoService.listarPorPaisYFecha(paisId, fecha));
    }

    @PostMapping
    public ResponseEntity<DiaFeriadoDto> crearFeriado(@RequestBody DiaFeriadoDto dto) {
        DiaFeriadoDto creado = feriadoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarFeriado(@PathVariable Integer id,
                                               @RequestBody DiaFeriadoDto dto) {
        return feriadoService.actualizar(id, dto)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFeriado(@PathVariable Integer id) {
        boolean eliminado = feriadoService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
