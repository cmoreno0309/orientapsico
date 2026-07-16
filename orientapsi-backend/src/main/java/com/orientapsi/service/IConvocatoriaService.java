package com.orientapsi.service;

import com.orientapsi.model.Convocatoria;
import java.util.List;
import java.util.Optional;

public interface IConvocatoriaService {
    Convocatoria save(Convocatoria convocatoria);
    Convocatoria update(Convocatoria convocatoria);
    void deleteById(Integer id);
    Convocatoria findById(Integer id);
    List<Convocatoria> findAll();
    Optional<Convocatoria> findConvocatoriaAbierta();
}
