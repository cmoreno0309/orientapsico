package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Sesion;
import com.orientapsi.repo.ISesionRepo;
import com.orientapsi.service.ISesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SesionServiceImpl implements ISesionService {

    private final ISesionRepo repo;

    @Override
    public Sesion save(Sesion sesion) { return repo.save(sesion); }

    @Override
    public Sesion update(Sesion sesion) { return repo.save(sesion); }

    @Override
    public void deleteById(Integer id) { repo.deleteById(id); }

    @Override
    public Sesion findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Sesión no encontrada: " + id));
    }

    @Override
    public List<Sesion> findAll() { return repo.findAll(); }

    @Override
    public List<Sesion> findByAsignacion(Integer idAsignacion) {
        return repo.findByAsignacionIdAsignacionOrderByNumeroSesionAsc(idAsignacion);
    }

    @Override
    public long countByAsignacionAndEstado(Integer idAsignacion, String estado) {
        return repo.countByAsignacionIdAsignacionAndEstado(idAsignacion, estado);
    }
}
