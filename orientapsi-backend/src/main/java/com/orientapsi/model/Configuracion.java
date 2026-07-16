package com.orientapsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "configuracion")
public class Configuracion {

    @Id
    @Column(length = 50)
    private String clave; // MAX_REPROGRAMACIONES, MAX_SESIONES, DIAS_ESPERA_CONFIRMACION, MAX_CASOS_POR_AD

    @Column(length = 50)
    private String valor;

    @Column(length = 200)
    private String descripcion;
}
