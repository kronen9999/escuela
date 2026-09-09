package com.example.demo.entities;


import com.example.demo.utils.ObjectUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "CURSOS")
@Slf4j
public class Curso {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")

    private Long id;

    @Column(name = "NOMBRE",nullable = false,length = 100,unique = true)
   private  String nombre;

    @Column(name = "DESCRIPCION",length = 200)
    private String descripcion;

    @Column(name = "CREDITOS",nullable = false,precision = 2)
    @Min(0)
    private  Integer creditos ;



    public void actualizar (String nombre,String descripcion,Integer creditos)
    {

        log.info("Actualizando informacion del curso");

        validarDatos(nombre,descripcion,creditos);

        this.nombre=nombre;
        this.descripcion=descripcion;
        this.creditos=creditos;

        log.info("Curso actualizada correctamente");

    }

    public void validarDatos (String nombre,String descripcion,Integer creditos)
    {
        log.info("Validando datos");
        ObjectUtils.validarNulls(nombre,"El campo de nombre no puede estar vacio");

        ObjectUtils.validarNulls(creditos,"Los campos de creditos no pueden estar vacios");

        validarCampos(nombre,descripcion,creditos);

    }





    private void validarCampos (String nombre, String descripcion,Integer creditos)
    {
        log.info("Validando los campos de nombre: "+ nombre + ", descripcion: "+descripcion +"y creditos: "+ creditos);

        if (nombre.trim().length()>100||nombre.length()<=0)
            throw  new IllegalArgumentException("El nombre debe de tener entre 1 y 100 caracteres");

        if (creditos<1||creditos>10)
            throw  new IllegalArgumentException("El rango valido para los creditos es de 1 - 10");

        if (descripcion!=null)
        {

            if (descripcion.trim().length()<=0||descripcion.length()>200)
                throw new IllegalArgumentException("La descrpcion debe de estar entre 1 y 200 caracteres");


        }


    }

}
