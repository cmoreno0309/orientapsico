package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.CierreCaso;
import com.orientapsi.repo.ICierreCasoRepo;
import com.orientapsi.service.ICierreCasoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CierreCasoServiceImpl implements ICierreCasoService {

    private final ICierreCasoRepo repo;

    @Override
    public CierreCaso save(CierreCaso cierre) { return repo.save(cierre); }

    @Override
    public CierreCaso update(CierreCaso cierre) { return repo.save(cierre); }

    @Override
    public void deleteById(Integer id) { repo.deleteById(id); }

    @Override
    public CierreCaso findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("Cierre no encontrado: " + id));
    }

    @Override
    public List<CierreCaso> findAll() { return repo.findAll(); }

    @Override
    public Optional<CierreCaso> findByCaso(Integer idCaso) { return repo.findByCasoIdCaso(idCaso); }
}
