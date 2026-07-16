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
@Table(name = "acompanante_directo")
public class AcompananteDirecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAd;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_salon", nullable = false)
    private Salon salon;

    @Column(length = 50)
    private String nombrePreferido;

    @Column(length = 150)
    private String correoInstitucional;

    @Column(length = 150)
    private String correoPersonal;

    @Column(length = 30)
    private String telefono;

    private Boolean compromisoConfirmado;

    @Column(length = 20)
    private String modalidadDisponible; // PRESENCIAL, VIRTUAL, AMBAS

    @Column(columnDefinition = "TEXT")
    private String preferenciaGeneroAtendido;

    @Column(columnDefinition = "TEXT")
    private String temasEvitar;

    @Column(nullable = false, length = 20)
    private String estado; // DISPONIBLE, OCUPADO, INACTIVO

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        if (estado == null) estado = "DISPONIBLE";
    }
}
