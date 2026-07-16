package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Salon;
import com.orientapsi.repo.ISalonRepo;
import com.orientapsi.service.ISalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements ISalonService {

    private final ISalonRepo repo;

    @Override
    public Salon save(Salon salon) { return repo.save(salon); }

    @Override
    public Salon update(Salon salon) { return repo.save(salon); }

    @Override
    public void deleteById(Integer id) { repo.deleteById(id); }

    @Override
    public Salon findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Salón no encontrado: " + id));
    }

    @Override
    public List<Salon> findAll() { return repo.findAll(); }

    @Override
    public List<Salon> findByCiclo(String ciclo) { return repo.findByCiclo(ciclo); }
}
