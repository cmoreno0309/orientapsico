package com.orientapsi.repo;

import com.orientapsi.model.Caso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICasoRepo extends JpaRepository<Caso, Integer> {
    Optional<Caso> findByCodigo(String codigo);
    List<Caso> findByEstado(String estado);
    List<Caso> findByConvocatoriaIdConvocatoria(Integer idConvocatoria);
    List<Caso> findByConsultanteIdConsultante(Integer idConsultante);
    boolean existsByConsultanteDocumentoIdentidad(String documentoIdentidad);
}
