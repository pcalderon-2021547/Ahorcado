package com.pablocalderon.ApiAhorcado.service;

import com.pablocalderon.ApiAhorcado.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> buscarPorId(Integer id);
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Integer id, Usuario usuario);
    void eliminar(Integer id);
}
