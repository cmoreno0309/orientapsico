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
@Table(name = "evaluacion_caso")
public class EvaluacionCaso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEvaluacion;

    @ManyToOne
    @JoinColumn(name = "id_caso", nullable = false)
    private Caso caso;

    @ManyToOne
    @JoinColumn(name = "id_practicante", nullable = false)
    private Usuario practicante;

    @Column(nullable = false, length = 5)
    private String clasificacion; // A, B, C

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(updatable = false)
    private LocalDateTime fechaEvaluacion;

    @PrePersist
    protected void onCreate() {
        fechaEvaluacion = LocalDateTime.now();
    }
}
