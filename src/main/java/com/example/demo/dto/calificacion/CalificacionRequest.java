package com.example.demo.dto.calificacion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Datos necesarios para registrar o actualizar una calificacion")
public record CalificacionRequest(

        @Schema(description = "Id de la inscripcion a la que se asocia la calificacion",example = "15")
        @NotNull(message = "El id de la inscripcion no puede estar vacio")
        @Positive(message = "El id de la inscripcion debe de ser positivo")
        Long idInscripcion,

        @Schema(description = "Calificacion de la inscripcion",example = "8.5")
        @NotNull(message = "La calificacion no puede estar vacia")
        @DecimalMin(value = "0.0",message = "La calificacion minima es 0.0")
        @DecimalMax(value = "10.0",message = "La calificacion maxima es 10.0")
        BigDecimal calificacion

) {
}
