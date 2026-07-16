package com.orientapsi.repo;

import com.orientapsi.model.DisponibilidadAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDisponibilidadAdRepo extends JpaRepository<DisponibilidadAd, Integer> {
    List<DisponibilidadAd> findByAcompananteDirectoIdAd(Integer idAd);
    List<DisponibilidadAd> findByDiaSemanaAndModalidad(String diaSemana, String modalidad);
}
