package com.orientapsi.service;

import com.orientapsi.model.Configuracion;
import java.util.List;

public interface IConfiguracionService {
    Configuracion save(Configuracion configuracion);
    Configuracion update(Configuracion configuracion);
    void deleteById(String clave);
    Configuracion findById(String clave);
    List<Configuracion> findAll();
    String getValor(String clave, String defaultValor);
}
