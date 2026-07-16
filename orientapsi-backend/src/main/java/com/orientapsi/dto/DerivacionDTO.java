package com.orientapsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DerivacionDTO {
    private Integer idDerivacion;

    @NotNull
    private Integer idCaso;

    private String institucionDestino;
    private String profesionalReferido;
    private String tipoServicio;
    private String observaciones;
    private Integer registradoPor;
    private LocalDate fechaDerivacion;
}
