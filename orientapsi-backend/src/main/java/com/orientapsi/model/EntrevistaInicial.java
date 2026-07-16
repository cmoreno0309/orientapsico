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
@Table(name = "entrevista_inicial")
public class EntrevistaInicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEntrevista;

    @ManyToOne
    @JoinColumn(name = "id_caso", nullable = false)
    private Caso caso;

    @ManyToOne
    @JoinColumn(name = "id_practicante", nullable = false)
    private Usuario practicante;

    @Column(nullable = false, length = 20)
    private String resultado; // ASIGNABLE, DERIVABLE

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    private LocalDate fechaEntrevista;

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }
}
