package com.orientapsi.repo;

import com.orientapsi.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISalonRepo extends JpaRepository<Salon, Integer> {
    List<Salon> findByCiclo(String ciclo);
}
