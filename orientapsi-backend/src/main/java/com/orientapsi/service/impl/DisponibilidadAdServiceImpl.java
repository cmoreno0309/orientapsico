package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.DisponibilidadAd;
import com.orientapsi.repo.IDisponibilidadAdRepo;
import com.orientapsi.service.IDisponibilidadAdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadAdServiceImpl implements IDisponibilidadAdService {

    private final IDisponibilidadAdRepo repo;

    @Override
    public DisponibilidadAd save(DisponibilidadAd disponibilidad) { return repo.save(disponibilidad); }

    @Override
    public DisponibilidadAd update(DisponibilidadAd disponibilidad) { return repo.save(disponibilidad); }

    @Override
    public void deleteById(Integer id) { repo.deleteById(id); }

    @Override
    public DisponibilidadAd findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Disponibilidad no encontrada: " + id));
    }

    @Override
    public List<DisponibilidadAd> findAll() { return repo.findAll(); }

    @Override
    public List<DisponibilidadAd> findByAd(Integer idAd) { return repo.findByAcompananteDirectoIdAd(idAd); }
}
