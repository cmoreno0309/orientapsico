package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Configuracion;
import com.orientapsi.repo.IConfiguracionRepo;
import com.orientapsi.service.IConfiguracionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfiguracionServiceImpl implements IConfiguracionService {

    private final IConfiguracionRepo repo;

    @Override
    public Configuracion save(Configuracion configuracion) { return repo.save(configuracion); }

    @Override
    public Configuracion update(Configuracion configuracion) { return repo.save(configuracion); }

    @Override
    public void deleteById(String clave) { repo.deleteById(clave); }

    @Override
    public Configuracion findById(String clave) {
        return repo.findById(clave).orElseThrow(() -> new ModelNotFoundException("Configuración no encontrada: " + clave));
    }

    @Override
    public List<Configuracion> findAll() { return repo.findAll(); }

    @Override
    public String getValor(String clave, String defaultValor) {
        return repo.findById(clave).map(Configuracion::getValor).orElse(defaultValor);
    }
}
