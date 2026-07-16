package com.orientapsi.service.impl;

import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Consultante;
import com.orientapsi.repo.IConsultanteRepo;
import com.orientapsi.service.IConsultanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultanteServiceImpl implements IConsultanteService {

    private final IConsultanteRepo repo;

    @Override
    public Mono<Consultante> save(Consultante consultante) {
        // La entidad R2DBC no tiene @PrePersist como la JPA, así que el hook
        // "fechaRegistro = LocalDateTime.now()" que antes vivía en el @Entity
        // se hace explícito aquí, en el punto donde arranca el plan reactivo.
        consultante.setFechaRegistro(LocalDateTime.now());
        return repo.save(consultante);
    }

    @Override
    public Mono<Consultante> update(Consultante consultante) {
        return repo.save(consultante);
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return repo.deleteById(id);
    }

    @Override
    public Mono<Consultante> findById(Integer id) {
        return repo.findById(id)
                .switchIfEmpty(Mono.error(new ModelNotFoundException("Consultante no encontrado: " + id)));
    }

    @Override
    public Flux<Consultante> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Consultante> findByDocumentoIdentidad(String documentoIdentidad) {
        return repo.findByDocumentoIdentidad(documentoIdentidad);
    }

    @Override
    public Mono<Boolean> fueAtendidoAntes(String documentoIdentidad) {
        return repo.existsByDocumentoIdentidadAndAtendidoAntesCeTrue(documentoIdentidad);
    }
}
