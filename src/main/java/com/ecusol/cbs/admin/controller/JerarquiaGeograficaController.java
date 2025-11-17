package com.ecusol.cbs.admin.controller;

import com.ecusol.cbs.admin.dto.JerarquiaGeograficaDto;
import com.ecusol.cbs.admin.service.JerarquiaGeograficaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/jerarquias")
@CrossOrigin(origins = "*")
public class JerarquiaGeograficaController {

    private final JerarquiaGeograficaService jerarquiaService;

    public JerarquiaGeograficaController(JerarquiaGeograficaService jerarquiaService) {
        this.jerarquiaService = jerarquiaService;
    }

    @GetMapping
    public ResponseEntity<List<JerarquiaGeograficaDto>> listarJerarquias() {
        return ResponseEntity.ok(jerarquiaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerJerarquia(@PathVariable Integer id) {
        return jerarquiaService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pais/{paisId}")
    public ResponseEntity<List<JerarquiaGeograficaDto>> listarPorPais(@PathVariable Integer paisId) {
        return ResponseEntity.ok(jerarquiaService.listarPorPais(paisId));
    }

    @PostMapping
    public ResponseEntity<JerarquiaGeograficaDto> crearJerarquia(@RequestBody JerarquiaGeograficaDto dto) {
        JerarquiaGeograficaDto creada = jerarquiaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarJerarquia(@PathVariable Integer id,
                                                 @RequestBody JerarquiaGeograficaDto dto) {
        return jerarquiaService.actualizar(id, dto)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJerarquia(@PathVariable Integer id) {
        boolean eliminado = jerarquiaService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
