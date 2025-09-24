package com.pablocalderon.ApiAhorcado.repository;

import com.pablocalderon.ApiAhorcado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}

