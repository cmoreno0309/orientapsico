package com.orientapsi.service;

import com.orientapsi.model.Derivacion;
import java.util.List;
import java.util.Optional;

public interface IDerivacionService {
    Derivacion save(Derivacion derivacion);
    Derivacion update(Derivacion derivacion);
    void deleteById(Integer id);
    Derivacion findById(Integer id);
    List<Derivacion> findAll();
    Optional<Derivacion> findByCaso(Integer idCaso);
}
