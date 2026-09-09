package com.example.demo.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos nesesarios para insertar un aula")
public record AulaRequest(

        @Schema(description = "Nombre del aula",example = "Aula 101")
        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,

        @Schema(description = "Capacidad del aula",example = "34")
        @Digits(integer = 4,fraction = 0,message = "LA capacidad no puede exceder los 4 digitos numericos")
        @NotNull(message = "La capacidad no puede estar vacia")
        Integer capacidad

) {
}
