package com.orientapsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CierreCasoDTO {
    private Integer idCierre;

    @NotNull
    private Integer idCaso;

    @NotNull
    private String motivo;

    private String observaciones;
    private Integer cerradoPor;
    private LocalDateTime fechaCierre;
}
