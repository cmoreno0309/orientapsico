package com.orientapsi.service;

import com.orientapsi.model.EntrevistaInicial;
import java.util.List;
import java.util.Optional;

public interface IEntrevistaInicialService {
    EntrevistaInicial save(EntrevistaInicial entrevista);
    EntrevistaInicial update(EntrevistaInicial entrevista);
    void deleteById(Integer id);
    EntrevistaInicial findById(Integer id);
    List<EntrevistaInicial> findAll();
    Optional<EntrevistaInicial> findByCaso(Integer idCaso);
}
