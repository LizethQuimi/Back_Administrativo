package com.ecusol.cbs.admin.controller;

import com.ecusol.cbs.admin.dto.PaisDto;
import com.ecusol.cbs.admin.service.PaisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/paises")
@CrossOrigin(origins = "*")
public class PaisController {

    private final PaisService paisService;

    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    
    @GetMapping
    public ResponseEntity<List<PaisDto>> listarPaises() {
        return ResponseEntity.ok(paisService.listar());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPais(@PathVariable Integer id) {
        return paisService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

  
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        return paisService.buscarPorNombre(nombre)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @GetMapping("/iso/{iso}")
    public ResponseEntity<?> buscarPorIso(@PathVariable String iso) {
        return paisService.buscarPorIsoAlpha2(iso)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

 
    @PostMapping
    public ResponseEntity<PaisDto> crearPais(@RequestBody PaisDto dto) {
        PaisDto creado = paisService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPais(@PathVariable Integer id,
                                            @RequestBody PaisDto dto) {
        return paisService.actualizar(id, dto)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPais(@PathVariable Integer id) {
        boolean eliminado = paisService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
