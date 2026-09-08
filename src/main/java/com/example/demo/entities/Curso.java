package com.example.demo.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "CURSOS")
public class Curso {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Long id;

    @Column(name = "NOMBRE",nullable = false,length = 100,unique = true)
   private  String nombre;

    @Column(name = "DESCRIPCION",length = 200)
    private String descripcion;

    @Column(name = "CREDITOS",nullable = false)
    @Min(0)
    private  Integer creditos ;




}
