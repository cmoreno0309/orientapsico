package com.orientapsi.repo;

import com.orientapsi.model.Convocatoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IConvocatoriaRepo extends JpaRepository<Convocatoria, Integer> {
    Optional<Convocatoria> findByEstado(String estado);
}
