package com.orientapsi.service;

import com.orientapsi.model.Caso;
import java.util.List;

public interface ICasoService {
    Caso save(Caso caso);
    Caso update(Caso caso);
    void deleteById(Integer id);
    Caso findById(Integer id);
    Caso findByCodigo(String codigo);
    List<Caso> findAll();
    List<Caso> findByEstado(String estado);
    List<Caso> findByConvocatoria(Integer idConvocatoria);
    boolean existeConsultantePrevio(String documentoIdentidad);
}
