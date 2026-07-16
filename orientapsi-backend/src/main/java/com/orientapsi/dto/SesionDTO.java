package com.orientapsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionDTO {
    private Integer idSesion;

    @NotNull
    private Integer idAsignacion;

    @NotNull
    private Integer numeroSesion;

    private LocalDate fechaProgramada;
    private LocalDate fechaReal;
    private String estado;
    private String observaciones;
    private LocalDateTime fechaRegistro;
}
