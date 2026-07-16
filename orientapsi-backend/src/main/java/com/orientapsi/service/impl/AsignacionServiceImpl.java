package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Asignacion;
import com.orientapsi.repo.IAsignacionRepo;
import com.orientapsi.service.IAsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignacionServiceImpl implements IAsignacionService {

    private final IAsignacionRepo repo;

    @Override
    public Asignacion save(Asignacion asignacion) { return repo.save(asignacion); }

    @Override
    public Asignacion update(Asignacion asignacion) { return repo.save(asignacion); }

    @Override
    public void deleteById(Integer id) { repo.deleteById(id); }

    @Override
    public Asignacion findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Asignación no encontrada: " + id));
    }

    @Override
    public List<Asignacion> findAll() { return repo.findAll(); }

    @Override
    public List<Asignacion> findActivas() { return repo.findByEstado("ACTIVA"); }

    @Override
    public Optional<Asignacion> findActivaByCaso(Integer idCaso) {
        return repo.findByCasoIdCasoAndEstado(idCaso, "ACTIVA");
    }

    @Override
    public long countActivasByAd(Integer idAd) {
        return repo.countByAcompananteDirectoIdAdAndEstado(idAd, "ACTIVA");
    }
}
