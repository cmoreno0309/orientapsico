package com.orientapsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalonDTO {
    private Integer idSalon;

    @NotNull
    private String nombre;

    private String ciclo;
    private Integer idDocente;
    private String nombreDocente;
}
