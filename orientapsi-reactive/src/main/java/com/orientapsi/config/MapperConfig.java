package com.orientapsi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Versión reducida del MapperConfig real: aquí solo se necesita el mapper
 * por defecto, porque Consultante no tiene relaciones anidadas (a diferencia
 * de Caso, Asignacion, etc. en el proyecto completo).
 */
@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
