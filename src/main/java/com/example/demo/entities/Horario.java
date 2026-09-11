package com.example.demo.entities;

import com.example.demo.enums.DiaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "HORARIOS")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HORARIO")
    private  Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO",nullable = false)
    private  Grupo grupo;

    @Enumerated (EnumType.STRING)
    @Column(name = "DIA",nullable = false)
    private DiaSemana diaSemana;

    @Column(name = "HORA_INICIO",length = 5,nullable = false)
    private String horaInicio;

    @Column(name = "HORA_FIN",length = 5,nullable = false)
    private String horaFIn;

    public void actualizar(
            Grupo grupo,
            DiaSemana diaSemana,
            String horaInicio,
            String horaFin
    ) {
        this.grupo = grupo;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFIn = horaFin;
    }
}
