package com.orientapsi.repo;

import com.orientapsi.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISesionRepo extends JpaRepository<Sesion, Integer> {
    List<Sesion> findByAsignacionIdAsignacion(Integer idAsignacion);
    List<Sesion> findByAsignacionIdAsignacionOrderByNumeroSesionAsc(Integer idAsignacion);
    long countByAsignacionIdAsignacionAndEstado(Integer idAsignacion, String estado);
}
