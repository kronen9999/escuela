package com.example.demo.dto.maestro.alumno.aula;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AulaRequest(

        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,

        @Digits(integer = 4,fraction = 0,message = "LA capacidad no puede exceder los 4 digitos numericos")
        @NotNull(message = "La capacidad no puede estar vacia")
        Short capacidad

) {
}
