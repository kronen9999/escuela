package com.example.demo.dto.calificacion;

import com.example.demo.dto.Inscripcion.Datos.InscripcionInscripcionDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Estructura del response de la calificacion")
public record CalificacionResponse(

        @Schema(description = "Id de la calificacion",example = "2")
        Long id,

        @Schema(description = "Datos de la inscripcion calificada")
        InscripcionInscripcionDto inscripcion,

        @Schema(description = "Calificacion de la inscripcion",example = "9")
        BigDecimal calificacion,

        @Schema(description = "Fecha en la que se registro la calificacion",example = "11/02/2026")
        String fechaRegistro

) {
}
