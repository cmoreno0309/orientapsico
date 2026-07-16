package com.orientapsi.repo;

import com.orientapsi.model.Derivacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDerivacionRepo extends JpaRepository<Derivacion, Integer> {
    Optional<Derivacion> findByCasoIdCaso(Integer idCaso);
}
