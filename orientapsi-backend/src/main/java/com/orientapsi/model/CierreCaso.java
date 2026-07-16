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
@Table(name = "cierre_caso")
public class CierreCaso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCierre;

    @ManyToOne
    @JoinColumn(name = "id_caso", nullable = false)
    private Caso caso;

    @Column(nullable = false, length = 50)
    private String motivo; // FINALIZADO, REPROGRAMACIONES, INASISTENCIAS, DERIVACION, DECISION_CONSULTANTE

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "cerrado_por")
    private Usuario cerradoPor;

    private LocalDateTime fechaCierre;

    @PrePersist
    protected void onCreate() {
        fechaCierre = LocalDateTime.now();
    }
}
