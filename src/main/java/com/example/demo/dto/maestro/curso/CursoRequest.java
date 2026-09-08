package com.example.demo.dto.maestro.curso;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CursoRequest(
        @NotBlank(message = "El nombre del curso no  puede estar vacio")
        String nombre,

        String descripcion,

        @NotNull(message = "Los creditos no pueden estar vacios")
        @Digits(message = "El numero no debe tener mas de dos digitos",integer = 2,fraction = 0)
        Short creditos


        ){}
