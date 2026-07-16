package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Convocatoria;
import com.orientapsi.repo.IConvocatoriaRepo;
import com.orientapsi.service.IConvocatoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConvocatoriaServiceImpl implements IConvocatoriaService {

    private final IConvocatoriaRepo repo;

    @Override
    public Convocatoria save(Convocatoria convocatoria) {
        return repo.save(convocatoria);
    }

    @Override
    public Convocatoria update(Convocatoria convocatoria) {
        return repo.save(convocatoria);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Convocatoria findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Convocatoria no encontrada: " + id));
    }

    @Override
    public List<Convocatoria> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Convocatoria> findConvocatoriaAbierta() {
        return repo.findByEstado("ABIERTA");
    }
}
