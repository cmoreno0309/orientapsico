package com.orientapsi.repo;

import com.orientapsi.model.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer> {

    Usuario findOneByNombre(String nombre);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.contrasena = :contrasena WHERE u.nombre = :nombre")
    void cambiarContrasena(@Param("nombre") String nombre, @Param("contrasena") String nuevaContrasena);
}
