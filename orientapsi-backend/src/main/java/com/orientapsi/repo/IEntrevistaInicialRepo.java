package com.orientapsi.repo;

import com.orientapsi.model.EntrevistaInicial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEntrevistaInicialRepo extends JpaRepository<EntrevistaInicial, Integer> {
    Optional<EntrevistaInicial> findByCasoIdCaso(Integer idCaso);
}
