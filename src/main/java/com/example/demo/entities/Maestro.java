package com.example.demo.entities;

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
@Table(name = "MAESTROS")
public class Maestro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAESTRO",nullable = false)
    private Long id;

    @Column(name = "NOMBRE",nullable = false,length = 50)
    private String nombre;


    @Column(name = "APELLIDO_PATERNO",nullable = false,length = 50)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO",nullable = false,length = 50)
    private String apellidoMaterno;

    @Column(name = "EMAIL",nullable = false,length = 100,unique = true)
    private String email;

    @Column(name = "TELEFONO",nullable = false,length = 10,unique = true)
    private String telefono;



}
