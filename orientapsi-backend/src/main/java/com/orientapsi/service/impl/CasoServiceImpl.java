package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Caso;
import com.orientapsi.repo.ICasoRepo;
import com.orientapsi.service.ICasoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CasoServiceImpl implements ICasoService {

    private final ICasoRepo repo;

    @Override
    public Caso save(Caso caso) {
        return repo.save(caso);
    }

    @Override
    public Caso update(Caso caso) {
        return repo.save(caso);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Caso findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Caso no encontrado: " + id));
    }

    @Override
    public Caso findByCodigo(String codigo) {
        return repo.findByCodigo(codigo).orElseThrow(() -> new ModelNotFoundException("Caso no encontrado: " + codigo));
    }

    @Override
    public List<Caso> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Caso> findByEstado(String estado) {
        return repo.findByEstado(estado);
    }

    @Override
    public List<Caso> findByConvocatoria(Integer idConvocatoria) {
        return repo.findByConvocatoriaIdConvocatoria(idConvocatoria);
    }

    @Override
    public boolean existeConsultantePrevio(String documentoIdentidad) {
        return repo.existsByConsultanteDocumentoIdentidad(documentoIdentidad);
    }
}
