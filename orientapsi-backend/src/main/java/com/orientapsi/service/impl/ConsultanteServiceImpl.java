package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Consultante;
import com.orientapsi.repo.IConsultanteRepo;
import com.orientapsi.service.IConsultanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultanteServiceImpl implements IConsultanteService {

    private final IConsultanteRepo repo;

    @Override
    public Consultante save(Consultante consultante) {
        return repo.save(consultante);
    }

    @Override
    public Consultante update(Consultante consultante) {
        return repo.save(consultante);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Consultante findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Consultante no encontrado: " + id));
    }

    @Override
    public List<Consultante> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Consultante> findByDocumentoIdentidad(String documentoIdentidad) {
        return repo.findByDocumentoIdentidad(documentoIdentidad);
    }

    @Override
    public boolean fueAtendidoAntes(String documentoIdentidad) {
        return repo.existsByDocumentoIdentidadAndAtendidoAntesCeTrue(documentoIdentidad);
    }
}
