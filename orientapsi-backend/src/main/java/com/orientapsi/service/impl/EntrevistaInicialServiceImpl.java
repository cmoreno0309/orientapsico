package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.EntrevistaInicial;
import com.orientapsi.repo.IEntrevistaInicialRepo;
import com.orientapsi.service.IEntrevistaInicialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntrevistaInicialServiceImpl implements IEntrevistaInicialService {

    private final IEntrevistaInicialRepo repo;

    @Override
    public EntrevistaInicial save(EntrevistaInicial entrevista) {
        return repo.save(entrevista);
    }

    @Override
    public EntrevistaInicial update(EntrevistaInicial entrevista) {
        return repo.save(entrevista);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public EntrevistaInicial findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Entrevista no encontrada: " + id));
    }

    @Override
    public List<EntrevistaInicial> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<EntrevistaInicial> findByCaso(Integer idCaso) {
        return repo.findByCasoIdCaso(idCaso);
    }
}
