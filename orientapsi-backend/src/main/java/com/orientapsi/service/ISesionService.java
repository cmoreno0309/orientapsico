package com.orientapsi.service;

import com.orientapsi.model.Sesion;
import java.util.List;

public interface ISesionService {
    Sesion save(Sesion sesion);
    Sesion update(Sesion sesion);
    void deleteById(Integer id);
    Sesion findById(Integer id);
    List<Sesion> findAll();
    List<Sesion> findByAsignacion(Integer idAsignacion);
    long countByAsignacionAndEstado(Integer idAsignacion, String estado);
}
