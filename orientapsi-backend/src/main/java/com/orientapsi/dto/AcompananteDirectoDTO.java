package com.orientapsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcompananteDirectoDTO {
    private Integer idAd;

    @NotNull
    private Integer idUsuario;

    @NotNull
    private Integer idSalon;

    private String nombrePreferido;
    private String correoInstitucional;
    private String correoPersonal;
    private String telefono;
    private Boolean compromisoConfirmado;
    private String modalidadDisponible;
    private String preferenciaGeneroAtendido;
    private String temasEvitar;
    private String estado;
    private LocalDateTime fechaRegistro;

    // Datos desnormalizados
    private String nombreSalon;
}
