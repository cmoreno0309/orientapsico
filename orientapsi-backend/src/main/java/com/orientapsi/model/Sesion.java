package com.orientapsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sesion")
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSesion;

    @ManyToOne
    @JoinColumn(name = "id_asignacion", nullable = false)
    private Asignacion asignacion;

    @Column(nullable = false)
    private Integer numeroSesion; // 1 a 10

    private LocalDate fechaProgramada;

    private LocalDate fechaReal;

    @Column(nullable = false, length = 20)
    private String estado; // PROGRAMADA, REALIZADA, REPROGRAMADA, PERDIDA

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        if (estado == null) estado = "PROGRAMADA";
    }
}
