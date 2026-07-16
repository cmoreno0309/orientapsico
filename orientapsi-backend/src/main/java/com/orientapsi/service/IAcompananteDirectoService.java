package com.orientapsi.service;

import com.orientapsi.model.AcompananteDirecto;
import java.util.List;

public interface IAcompananteDirectoService {
    AcompananteDirecto save(AcompananteDirecto ad);
    AcompananteDirecto update(AcompananteDirecto ad);
    void deleteById(Integer id);
    AcompananteDirecto findById(Integer id);
    List<AcompananteDirecto> findAll();
    List<AcompananteDirecto> findDisponibles();
    List<AcompananteDirecto> findBySalon(Integer idSalon);
}
