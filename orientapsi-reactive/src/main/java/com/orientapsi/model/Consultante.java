package com.orientapsi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Misma entidad que en el backend real (com.orientapsi.model.Consultante),
 * pero mapeada con anotaciones de Spring Data Relational (R2DBC) en vez de
 * JPA/Hibernate. Apunta a la MISMA tabla "consultante" en Postgres.
 *
 * Diferencias clave respecto a la versión JPA:
 * - @Id es org.springframework.data.annotation.Id (no jakarta.persistence.Id)
 * - No existe @GeneratedValue: R2DBC asume autogenerado si el id es null al guardar
 * - No hay @PrePersist: ese hook de ciclo de vida no existe en R2DBC,
 *   por eso fechaRegistro se setea manualmente en el Service (ver ConsultanteServiceImpl)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("consultante")
public class Consultante {

    @Id
    private Integer idConsultante;

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
