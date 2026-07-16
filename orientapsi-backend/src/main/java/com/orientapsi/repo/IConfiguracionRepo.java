package com.orientapsi.repo;

import com.orientapsi.model.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConfiguracionRepo extends JpaRepository<Configuracion, String> {
}
