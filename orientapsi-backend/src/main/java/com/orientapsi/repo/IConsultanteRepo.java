package com.orientapsi.repo;

import com.orientapsi.model.Consultante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IConsultanteRepo extends JpaRepository<Consultante, Integer> {
    Optional<Consultante> findByDocumentoIdentidad(String documentoIdentidad);
    Optional<Consultante> findByCorreo(String correo);
    boolean existsByDocumentoIdentidadAndAtendidoAntesCeTrue(String documentoIdentidad);
}
