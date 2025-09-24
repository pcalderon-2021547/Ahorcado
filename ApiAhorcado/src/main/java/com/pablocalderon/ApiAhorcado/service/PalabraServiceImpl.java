package com.pablocalderon.ApiAhorcado.service;

import com.pablocalderon.ApiAhorcado.model.Palabra;
import com.pablocalderon.ApiAhorcado.repository.PalabraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalabraServiceImpl implements PalabraService {

    private final PalabraRepository palabraRepository;

    public PalabraServiceImpl(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    @Override
    public List<Palabra> listar() {
        return palabraRepository.findAll();
    }

    @Override
    public Optional<Palabra> buscarPorId(Integer id) {
        return palabraRepository.findById(id);
    }

    @Override
    public Palabra guardar(Palabra palabra) {
        if (palabra == null || palabra.getPalabra() == null || palabra.getPalabra().trim().isEmpty()) {
            throw new RuntimeException("La palabra no puede estar vacía");
        }
        if (palabra.getPista() == null || palabra.getPista().trim().isEmpty()) {
            throw new RuntimeException("La pista no puede estar vacía");
        }
        return palabraRepository.save(palabra);
    }

    @Override
    public Palabra actualizar(Integer id, Palabra palabra) {
        Palabra existente = palabraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + id));

        if (palabra == null || palabra.getPalabra() == null || palabra.getPalabra().trim().isEmpty()) {
            throw new RuntimeException("La palabra no puede estar vacía");
        }
        if (palabra.getPista() == null || palabra.getPista().trim().isEmpty()) {
            throw new RuntimeException("La pista no puede estar vacía");
        }

        existente.setPalabra(palabra.getPalabra());
        existente.setPista(palabra.getPista());
        return palabraRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!palabraRepository.existsById(id)) {
            throw new RuntimeException("La palabra con ID " + id + " no existe");
        }
        palabraRepository.deleteById(id);
    }
}
