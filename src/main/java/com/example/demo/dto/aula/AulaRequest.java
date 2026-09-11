package com.example.demo.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos nesesarios para insertar un aula")
public record AulaRequest(

        @Schema(description = "Nombre del aula",example = "Aula 101")
        @Size(min = 1,max = 100,message ="El nombre del aula debe de estar entre 1 y 100 caracteres")
        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,

        @Schema(description = "Capacidad del aula",example = "34")
        @Digits(integer = 4,fraction = 0,message = "LA capacidad no puede exceder los 4 digitos numericos")
        @NotNull(message = "La capacidad no puede estar vacia")
        Integer capacidad

) {
}
