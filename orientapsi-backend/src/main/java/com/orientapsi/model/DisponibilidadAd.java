package com.orientapsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "disponibilidad_ad")
public class DisponibilidadAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDisponibilidad;

    @ManyToOne
    @JoinColumn(name = "id_ad", nullable = false)
    private AcompananteDirecto acompananteDirecto;

    @Column(nullable = false, length = 15)
    private String modalidad; // PRESENCIAL, VIRTUAL

    @Column(nullable = false, length = 10)
    private String diaSemana; // LUNES, MARTES, etc.

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;
}
