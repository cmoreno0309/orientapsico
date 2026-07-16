package com.orientapsi.repo;

import com.orientapsi.model.AcompananteDirecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAcompananteDirectoRepo extends JpaRepository<AcompananteDirecto, Integer> {
    List<AcompananteDirecto> findByEstado(String estado);
    List<AcompananteDirecto> findBySalonIdSalon(Integer idSalon);
    List<AcompananteDirecto> findByEstadoAndModalidadDisponible(String estado, String modalidad);
}
