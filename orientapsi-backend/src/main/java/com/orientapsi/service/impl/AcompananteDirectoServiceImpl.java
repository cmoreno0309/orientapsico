package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.AcompananteDirecto;
import com.orientapsi.repo.IAcompananteDirectoRepo;
import com.orientapsi.service.IAcompananteDirectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcompananteDirectoServiceImpl implements IAcompananteDirectoService {

    private final IAcompananteDirectoRepo repo;

    @Override
    public AcompananteDirecto save(AcompananteDirecto ad) { return repo.save(ad); }

    @Override
    public AcompananteDirecto update(AcompananteDirecto ad) { return repo.save(ad); }

    @Override
    public void deleteById(Integer id) { repo.deleteById(id); }

    @Override
    public AcompananteDirecto findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("AD no encontrado: " + id));
    }

    @Override
    public List<AcompananteDirecto> findAll() { return repo.findAll(); }

    @Override
    public List<AcompananteDirecto> findDisponibles() { return repo.findByEstado("DISPONIBLE"); }

    @Override
    public List<AcompananteDirecto> findBySalon(Integer idSalon) { return repo.findBySalonIdSalon(idSalon); }
}
