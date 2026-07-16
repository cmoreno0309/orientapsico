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
public class ConsultanteDTO {
    private Integer idConsultante;

    @NotNull
    @Size(max = 200)
    private String nombreCompleto;

    private String documentoIdentidad;
    private Integer edad;
    private String genero;
    private String estadoCivil;
    private String paisResidencia;
    private String provincia;
    private String distrito;
    private String telefono;
    private String telefonoEmergencia;
    private String nombreContactoEmergencia;
    private String parentescoEmergencia;
    private String correo;
    private Boolean esAlumnoUarm;
    private Boolean esTrabajadorUarm;
    private Boolean tieneSituacionRiesgo;
    private String enfermedadTratamiento;
    private Boolean atendidoAntesCe;
    private Boolean atencionPsicologicaPrevia;
    private String detalleAtencionPsicologica;
    private Boolean atencionPsiquiatricaPrevia;
    private String detalleAtencionPsiquiatrica;
    private String motivoConsulta;
    private String tiempoConPreocupacion;
    private String impactoEnVida;
    private String diagnosticosPrevios;
    private String puedeCostearServicio;
    private String expectativaSesiones;
    private String expectativaPostSesiones;
    private String modalidadPreferida;
    private Boolean espacioPrivado;
    private String preferenciaGeneroAd;
    private Boolean tieneDisponibilidadFija;
    private String disponibilidadHoraria;
    private String comoSeEntero;
    private LocalDateTime fechaRegistro;
}
