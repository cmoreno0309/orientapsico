package com.orientapsi.service;

import com.orientapsi.model.DisponibilidadAd;
import java.util.List;

public interface IDisponibilidadAdService {
    DisponibilidadAd save(DisponibilidadAd disponibilidad);
    DisponibilidadAd update(DisponibilidadAd disponibilidad);
    void deleteById(Integer id);
    DisponibilidadAd findById(Integer id);
    List<DisponibilidadAd> findAll();
    List<DisponibilidadAd> findByAd(Integer idAd);
}
