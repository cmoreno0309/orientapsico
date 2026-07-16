package com.orientapsi.service;

import com.orientapsi.model.Asignacion;
import java.util.List;
import java.util.Optional;

public interface IAsignacionService {
    Asignacion save(Asignacion asignacion);
    Asignacion update(Asignacion asignacion);
    void deleteById(Integer id);
    Asignacion findById(Integer id);
    List<Asignacion> findAll();
    List<Asignacion> findActivas();
    Optional<Asignacion> findActivaByCaso(Integer idCaso);
    long countActivasByAd(Integer idAd);
}
