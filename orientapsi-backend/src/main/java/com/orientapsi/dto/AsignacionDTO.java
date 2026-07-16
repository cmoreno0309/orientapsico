package com.orientapsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionDTO {
    private Integer idAsignacion;

    @NotNull
    private Integer idCaso;

    @NotNull
    private Integer idAd;

    private String modalidad;
    private LocalDate fechaAsignacion;
    private LocalDate fechaCierre;
    private String estado;
    private Integer asignadoPor;

    // Datos desnormalizados
    private String nombreConsultante;
    private String nombreAd;
    private String codigoCaso;
}
