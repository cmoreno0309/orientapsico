package com.orientapsi.service;

import com.orientapsi.model.EvaluacionCaso;
import java.util.List;

public interface IEvaluacionCasoService {
    EvaluacionCaso save(EvaluacionCaso evaluacion);
    EvaluacionCaso update(EvaluacionCaso evaluacion);
    void deleteById(Integer id);
    EvaluacionCaso findById(Integer id);
    List<EvaluacionCaso> findAll();
    List<EvaluacionCaso> findByCaso(Integer idCaso);
    long countByCaso(Integer idCaso);
    boolean yaEvaluo(Integer idCaso, Integer idPracticante);
}
