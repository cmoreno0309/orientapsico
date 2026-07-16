package com.orientapsi.repo;

import com.orientapsi.model.Consultante;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * Equivalente reactivo de:
 *   public interface IConsultanteRepo extends JpaRepository<Consultante, Integer>
 *
 * Cambios respecto al original:
 * - JpaRepository -> ReactiveCrudRepository (no bloqueante, usa R2DBC por debajo)
 * - Cada método que antes devolvía T u Optional<T> ahora devuelve Mono<T>
 * - boolean -> Mono<Boolean> (incluso una consulta de "existe o no" es asíncrona)
 *
 * Los métodos derivados (findByX, existsByXAndY) funcionan exactamente igual
 * por convención de nombres, solo cambia el tipo de retorno envolvente.
 */
public interface IConsultanteRepo extends ReactiveCrudRepository<Consultante, Integer> {

    Mono<Consultante> findByDocumentoIdentidad(String documentoIdentidad);

    Mono<Consultante> findByCorreo(String correo);

    Mono<Boolean> existsByDocumentoIdentidadAndAtendidoAntesCeTrue(String documentoIdentidad);
}
