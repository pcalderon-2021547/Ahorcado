package com.pablocalderon.ApiAhorcado.controller;

import com.pablocalderon.ApiAhorcado.model.Palabra;
import com.pablocalderon.ApiAhorcado.service.PalabraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/palabras")
public class PalabraController {

    private final PalabraService palabraService;

    public PalabraController(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Palabra> palabras = palabraService.listar();
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Lista de palabras obtenida exitosamente");
            response.put("data", palabras);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener palabras: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Palabra palabra = palabraService.buscarPorId(id)
                    .orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + id));

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Palabra obtenida exitosamente");
            response.put("data", palabra);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Palabra palabra) {
        try {
            Palabra nuevaPalabra = palabraService.guardar(palabra);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Palabra creada exitosamente");
            response.put("data", nuevaPalabra);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al guardar la palabra: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Palabra palabra) {
        try {
            Palabra palabraActualizada = palabraService.actualizar(id, palabra);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Palabra actualizada exitosamente");
            response.put("data", palabraActualizada);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la palabra: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            palabraService.eliminar(id);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Palabra eliminada exitosamente");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar la palabra: " + e.getMessage());
        }
    }
}
