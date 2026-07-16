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
@Table(name = "caso")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCaso;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo; // CE-2026-001

    @ManyToOne
    @JoinColumn(name = "id_consultante", nullable = false)
    private Consultante consultante;

    @ManyToOne
    @JoinColumn(name = "id_convocatoria", nullable = false)
    private Convocatoria convocatoria;

    @Column(nullable = false, length = 30)
    private String estado; // PENDIENTE_EVALUACION, EN_EVALUACION, EVALUADO, PENDIENTE_ENTREVISTA, PENDIENTE_CONFIRMACION, CONFIRMADO, ACTIVO, DERIVADO, HISTORICO, CERRADO

    @Column(length = 5)
    private String clasificacionFinal; // A, B, C

    private Boolean requiereEntrevista;

    @Column(nullable = false)
    private Integer contadorReprogramaciones;

    @Column(nullable = false)
    private Integer contadorInasistencias;

    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (contadorReprogramaciones == null) contadorReprogramaciones = 0;
        if (contadorInasistencias == null) contadorInasistencias = 0;
        if (requiereEntrevista == null) requiereEntrevista = false;
        if (estado == null) estado = "PENDIENTE_EVALUACION";
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}
