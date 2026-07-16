package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.EvaluacionCaso;
import com.orientapsi.repo.IEvaluacionCasoRepo;
import com.orientapsi.service.IEvaluacionCasoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluacionCasoServiceImpl implements IEvaluacionCasoService {

    private final IEvaluacionCasoRepo repo;

    @Override
    public EvaluacionCaso save(EvaluacionCaso evaluacion) {
        return repo.save(evaluacion);
    }

    @Override
    public EvaluacionCaso update(EvaluacionCaso evaluacion) {
        return repo.save(evaluacion);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public EvaluacionCaso findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Evaluación no encontrada: " + id));
    }

    @Override
    public List<EvaluacionCaso> findAll() {
        return repo.findAll();
    }

    @Override
    public List<EvaluacionCaso> findByCaso(Integer idCaso) {
        return repo.findByCasoIdCaso(idCaso);
    }

    @Override
    public long countByCaso(Integer idCaso) {
        return repo.countByCasoIdCaso(idCaso);
    }

    @Override
    public boolean yaEvaluo(Integer idCaso, Integer idPracticante) {
        return repo.existsByCasoIdCasoAndPracticanteIdUsuario(idCaso, idPracticante);
    }
}
