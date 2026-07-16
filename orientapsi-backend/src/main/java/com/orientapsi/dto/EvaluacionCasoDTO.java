package com.orientapsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionCasoDTO {
    private Integer idEvaluacion;

    @NotNull
    private Integer idCaso;

    @NotNull
    private Integer idPracticante;

    @NotNull
    @Size(max = 5)
    private String clasificacion;

    private String comentario;
    private LocalDateTime fechaEvaluacion;

    // Dato desnormalizado
    private String nombrePracticante;
}
