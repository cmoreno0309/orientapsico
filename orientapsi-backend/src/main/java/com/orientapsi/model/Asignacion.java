package com.orientapsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asignacion")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAsignacion;

    @ManyToOne
    @JoinColumn(name = "id_caso", nullable = false)
    private Caso caso;

    @ManyToOne
    @JoinColumn(name = "id_ad", nullable = false)
    private AcompananteDirecto acompananteDirecto;

    @Column(length = 15)
    private String modalidad; // PRESENCIAL, VIRTUAL

    @Column(nullable = false)
    private LocalDate fechaAsignacion;

    private LocalDate fechaCierre;

    @Column(nullable = false, length = 20)
    private String estado; // ACTIVA, CERRADA

    @ManyToOne
    @JoinColumn(name = "asignado_por")
    private Usuario asignadoPor;

    @PrePersist
    protected void onCreate() {
        if (estado == null) estado = "ACTIVA";
    }
}
