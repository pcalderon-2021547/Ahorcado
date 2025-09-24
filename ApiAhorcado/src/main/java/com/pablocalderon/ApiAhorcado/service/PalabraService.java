package com.pablocalderon.ApiAhorcado.service;

import com.pablocalderon.ApiAhorcado.model.Palabra;
import java.util.List;
import java.util.Optional;

public interface PalabraService {
    List<Palabra> listar();
    Optional<Palabra> buscarPorId(Integer id);
    Palabra guardar(Palabra palabra);
    Palabra actualizar(Integer id, Palabra palabra);
    void eliminar(Integer id);
}
