package com.example.demo.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion del aula")
public record AulaResponse(

        @Schema(description = "Identificador del aula", example = "1")
        Long id,

        @Schema(description = "Nombre del aula", example = "Aula 101")
        String nombre,

        @Schema(description = "Capacidad del aula", example = "30")
        Integer capacidad
) {
}
