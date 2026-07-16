package com.orientapsi.repo;

import com.orientapsi.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAsignacionRepo extends JpaRepository<Asignacion, Integer> {
    Optional<Asignacion> findByCasoIdCasoAndEstado(Integer idCaso, String estado);
    List<Asignacion> findByAcompananteDirectoIdAdAndEstado(Integer idAd, String estado);
    List<Asignacion> findByEstado(String estado);
    long countByAcompananteDirectoIdAdAndEstado(Integer idAd, String estado);
}
