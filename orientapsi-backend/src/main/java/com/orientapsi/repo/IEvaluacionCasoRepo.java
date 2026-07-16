package com.orientapsi.repo;

import com.orientapsi.model.EvaluacionCaso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEvaluacionCasoRepo extends JpaRepository<EvaluacionCaso, Integer> {
    List<EvaluacionCaso> findByCasoIdCaso(Integer idCaso);
    long countByCasoIdCaso(Integer idCaso);
    boolean existsByCasoIdCasoAndPracticanteIdUsuario(Integer idCaso, Integer idPracticante);
}
