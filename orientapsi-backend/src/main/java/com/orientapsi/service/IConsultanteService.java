package com.orientapsi.service;

import com.orientapsi.model.Consultante;
import java.util.List;
import java.util.Optional;

public interface IConsultanteService {
    Consultante save(Consultante consultante);
    Consultante update(Consultante consultante);
    void deleteById(Integer id);
    Consultante findById(Integer id);
    List<Consultante> findAll();
    Optional<Consultante> findByDocumentoIdentidad(String documentoIdentidad);
    boolean fueAtendidoAntes(String documentoIdentidad);
}
