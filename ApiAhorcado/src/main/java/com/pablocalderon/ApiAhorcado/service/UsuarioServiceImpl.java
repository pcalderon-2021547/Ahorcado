package com.pablocalderon.ApiAhorcado.service;

import com.pablocalderon.ApiAhorcado.model.Usuario;
import com.pablocalderon.ApiAhorcado.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        if (usuario == null || usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
            throw new RuntimeException("El nombre de usuario no puede estar vacío");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new RuntimeException("El email no puede estar vacío");
        }
        if (usuario.getContra() == null || usuario.getContra().trim().isEmpty()) {
            throw new RuntimeException("La contraseña no puede estar vacía");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        if (usuario == null || usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
            throw new RuntimeException("El nombre de usuario no puede estar vacío");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new RuntimeException("El email no puede estar vacío");
        }
        if (usuario.getContra() == null || usuario.getContra().trim().isEmpty()) {
            throw new RuntimeException("La contraseña no puede estar vacía");
        }

        existente.setNombreUsuario(usuario.getNombreUsuario());
        existente.setEmail(usuario.getEmail());
        existente.setContra(usuario.getContra());
        return usuarioRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("El usuario con ID " + id + " no existe");
        }
        usuarioRepository.deleteById(id);
    }
}
