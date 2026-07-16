package com.orientapsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultante")
public class Consultante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idConsultante;

    @Column(nullable = false, length = 200)
    private String nombreCompleto;

    @Column(length = 30)
    private String documentoIdentidad;

    private Integer edad;

    @Column(length = 30)
    private String genero;

    @Column(length = 30)
    private String estadoCivil;

    @Column(length = 50)
    private String paisResidencia;

    @Column(length = 100)
    private String provincia;

    @Column(length = 100)
    private String distrito;

    @Column(length = 30)
    private String telefono;

    @Column(length = 30)
    private String telefonoEmergencia;

    @Column(length = 200)
    private String nombreContactoEmergencia;

    @Column(length = 50)
    private String parentescoEmergencia;

    @Column(length = 150)
    private String correo;

    // Preguntas filtro
    private Boolean esAlumnoUarm;

    private Boolean esTrabajadorUarm;

    private Boolean tieneSituacionRiesgo;

    // Antecedentes
    @Column(columnDefinition = "TEXT")
    private String enfermedadTratamiento;

    private Boolean atendidoAntesCe;

    private Boolean atencionPsicologicaPrevia;

    @Column(columnDefinition = "TEXT")
    private String detalleAtencionPsicologica;

    private Boolean atencionPsiquiatricaPrevia;

    @Column(columnDefinition = "TEXT")
    private String detalleAtencionPsiquiatrica;

    // Motivo y expectativas
    @Column(columnDefinition = "TEXT")
    private String motivoConsulta;

    @Column(columnDefinition = "TEXT")
    private String tiempoConPreocupacion;

    @Column(columnDefinition = "TEXT")
    private String impactoEnVida;

    @Column(columnDefinition = "TEXT")
    private String diagnosticosPrevios;

    @Column(columnDefinition = "TEXT")
    private String puedeCostearServicio;

    @Column(columnDefinition = "TEXT")
    private String expectativaSesiones;

    @Column(columnDefinition = "TEXT")
    private String expectativaPostSesiones;

    // Preferencias
    @Column(length = 30)
    private String modalidadPreferida; // PRESENCIAL, VIRTUAL, CUALQUIERA

    private Boolean espacioPrivado;

    @Column(length = 30)
    private String preferenciaGeneroAd; // MUJER, VARON, SIN_PREFERENCIA

    private Boolean tieneDisponibilidadFija;

    @Column(columnDefinition = "TEXT")
    private String disponibilidadHoraria;

    @Column(length = 100)
    private String comoSeEntero;

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }
}
