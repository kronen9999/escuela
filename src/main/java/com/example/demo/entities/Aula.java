package com.example.demo.entities;


import com.example.demo.utils.ObjectUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "AULAS")
@Slf4j
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AULA")
    private Long id;

    @Column(name = "NOMBRE",nullable = false,unique = true,length = 100)
    private String nombre;

    @Column(name = "CAPACIDAD",nullable = false)
    private Integer capacidad;

    public void actualizar (String nombre,Integer capacidad)
    {

        log.info("Actualizando informacion del aula");

        validarDatos(nombre,capacidad);

        this.nombre=nombre;
        this.capacidad=capacidad;

        log.info("Aula actualizada correctamente");

    }

    public void validarDatos (String nombre,Integer capacidad)
    {
        log.info("Validando datos");
        ObjectUtils.validarNulls(nombre,"El campo de nombre no puede estar vacio");

        ObjectUtils.validarNulls(capacidad,"El campo de capacidad no puede estar vacio");

        validarCampos(nombre,capacidad);

    }





    private void validarCampos (String nombre, Integer capacidad)
    {
        log.info("Validando los campos de nombre: "+ nombre + "y capacidad "+capacidad);

        if (nombre.trim().length()>100||nombre.length()<=0)
            throw  new IllegalArgumentException("El nombre debe de tener entre 1 y 100 caracteres");

        if (capacidad<0)
            throw  new IllegalArgumentException("la capacidad del salon no puede ser menor a 0");


    }





}
