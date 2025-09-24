package com.pablocalderon.ApiAhorcado.controller;

import com.pablocalderon.ApiAhorcado.model.Usuario;
import com.pablocalderon.ApiAhorcado.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Usuario> usuarios = usuarioService.listar();
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Lista de usuarios obtenida exitosamente");
            response.put("data", usuarios);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensaje", "Error al obtener usuarios", "error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Usuario usuario = usuarioService.buscarPorId(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Usuario obtenido exitosamente");
            response.put("data", usuario);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Error al buscar usuario", "error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Usuario creado exitosamente");
            response.put("data", nuevoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensaje", "Error al guardar el usuario", "error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            Usuario actualizado = usuarioService.actualizar(id, usuario);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Usuario actualizado exitosamente");
            response.put("data", actualizado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensaje", "Error al actualizar el usuario", "error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            usuarioService.eliminar(id);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Usuario eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Error al eliminar el usuario", "error", e.getMessage()));
        }
    }
}
