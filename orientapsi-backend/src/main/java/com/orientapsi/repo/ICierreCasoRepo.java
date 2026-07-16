package com.orientapsi.repo;

import com.orientapsi.model.CierreCaso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICierreCasoRepo extends JpaRepository<CierreCaso, Integer> {
    Optional<CierreCaso> findByCasoIdCaso(Integer idCaso);
}
