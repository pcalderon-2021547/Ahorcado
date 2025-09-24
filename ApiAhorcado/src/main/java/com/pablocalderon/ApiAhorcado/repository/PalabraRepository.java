package com.pablocalderon.ApiAhorcado.repository;

import com.pablocalderon.ApiAhorcado.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {
}

