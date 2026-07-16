package com.orientapsi.service;

import com.orientapsi.model.Salon;
import java.util.List;

public interface ISalonService {
    Salon save(Salon salon);
    Salon update(Salon salon);
    void deleteById(Integer id);
    Salon findById(Integer id);
    List<Salon> findAll();
    List<Salon> findByCiclo(String ciclo);
}
