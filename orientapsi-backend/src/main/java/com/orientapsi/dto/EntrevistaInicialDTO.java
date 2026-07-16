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
public class EntrevistaInicialDTO {
    private Integer idEntrevista;

    @NotNull
    private Integer idCaso;

    @NotNull
    private Integer idPracticante;

    @NotNull
    private String resultado;

    private String observaciones;
    private LocalDate fechaEntrevista;
    private LocalDateTime fechaRegistro;
}
