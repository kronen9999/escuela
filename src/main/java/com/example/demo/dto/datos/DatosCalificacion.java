package com.example.demo.dto.datos;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Datos de una calificacion")
public record DatosCalificacion(

        @Schema(description = "Nombre del curso",example = "Matematicas I")
        String curso,

        @Schema(description = "Descripcion del curso",example = "Introduccion a las matematicas")
        String periodo,

        @Schema(description = "Calififacion del curso",example = "5")
        BigDecimal calificacion

) {
}
