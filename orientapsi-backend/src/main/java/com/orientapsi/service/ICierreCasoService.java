package com.orientapsi.service;

import com.orientapsi.model.CierreCaso;
import java.util.List;
import java.util.Optional;

public interface ICierreCasoService {
    CierreCaso save(CierreCaso cierre);
    CierreCaso update(CierreCaso cierre);
    void deleteById(Integer id);
    CierreCaso findById(Integer id);
    List<CierreCaso> findAll();
    Optional<CierreCaso> findByCaso(Integer idCaso);
}
