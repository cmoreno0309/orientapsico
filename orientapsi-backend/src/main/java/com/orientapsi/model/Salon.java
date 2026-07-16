package com.orientapsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salon")
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSalon;

    @Column(nullable = false, length = 50)
    private String nombre; // Salon 1, Salon 2, Salon 3

    @Column(length = 20)
    private String ciclo; // 2026-I

    @ManyToOne
    @JoinColumn(name = "id_docente")
    private Usuario docente;
}
