package com.orientapsi.service;

import com.orientapsi.model.Consultante;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Equivalente reactivo de IConsultanteService.
 *
 * Comparación con el original:
 *   Consultante save(...)              -> Mono<Consultante> save(...)
 *   void deleteById(...)               -> Mono<Void> deleteById(...)
 *   List<Consultante> findAll()        -> Flux<Consultante> findAll()   (varios elementos -> Flux)
 *   Optional<Consultante> findByDoc... -> Mono<Consultante> findByDoc...
 *   boolean fueAtendidoAntes(...)      -> Mono<Boolean> fueAtendidoAntes(...)
 */
public interface IConsultanteService {
    Mono<Consultante> save(Consultante consultante);
    Mono<Consultante> update(Consultante consultante);
    Mono<Void> deleteById(Integer id);
    Mono<Consultante> findById(Integer id);
    Flux<Consultante> findAll();
    Mono<Consultante> findByDocumentoIdentidad(String documentoIdentidad);
    Mono<Boolean> fueAtendidoAntes(String documentoIdentidad);
}
