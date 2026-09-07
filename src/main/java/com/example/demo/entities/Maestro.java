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
    Long id;

    @Column(name = "NOMBRE",nullable = false,length = 50)
    String nombre;


    @Column(name = "APELLIDO_PATERNO",nullable = false,length = 50)
    String apellidoPaterno;

    @Column(name = "APELLIDO_Materno",nullable = false,length = 50)
    String apellidoMaterno;

    @Column(name = "EMAIL",nullable = false,length = 100,unique = true)
    String email;

    @Column(name = "TELEFONO",nullable = false,length = 10,unique = true)
    String telefono;



}
