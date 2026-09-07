package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "ALUMNOS")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALUMNO")
    private Long id;

    @Column(name = "NOMBRE",nullable = false,length = 50)
    private String nombre;


    @Column(name = "APELLIDO_PATERNO",nullable = false,length = 50)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO",nullable = false,length = 50)
    private String apellidoMaterno;

    @Column(name = "EMAIL",nullable = false,length = 100,unique = true)
    private String email;

    @Column(name = "MATRICULA",length = 10,unique = true,nullable = false)
    private String matricula;

    @Column(name = "FECHA_INGRESO",length = 10)
    private LocalDate fecha;


}
