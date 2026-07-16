package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Derivacion;
import com.orientapsi.repo.IDerivacionRepo;
import com.orientapsi.service.IDerivacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DerivacionServiceImpl implements IDerivacionService {

    private final IDerivacionRepo repo;

    @Override
    public Derivacion save(Derivacion derivacion) {
        return repo.save(derivacion);
    }

    @Override
    public Derivacion update(Derivacion derivacion) {
        return repo.save(derivacion);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Derivacion findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Derivación no encontrada: " + id));
    }

    @Override
    public List<Derivacion> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Derivacion> findByCaso(Integer idCaso) {
        return repo.findByCasoIdCaso(idCaso);
    }
}
