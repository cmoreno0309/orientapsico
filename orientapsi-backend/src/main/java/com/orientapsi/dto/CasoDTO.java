package com.orientapsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasoDTO {
    private Integer idCaso;
    private String codigo;

    @NotNull
    private Integer idConsultante;

    @NotNull
    private Integer idConvocatoria;

    private String estado;
    private String clasificacionFinal;
    private Boolean requiereEntrevista;
    private Integer contadorReprogramaciones;
    private Integer contadorInasistencias;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // Datos desnormalizados para la bandeja de evaluación
    private String nombreConsultante;
    private String motivoConsulta;
    private String disponibilidadHoraria;
}
