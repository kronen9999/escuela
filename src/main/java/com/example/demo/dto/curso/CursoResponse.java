package com.example.demo.dto.curso;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion del curso")
public record CursoResponse(

        @Schema(description = "Identificador del curso", example = "1")
        Long id,

        @Schema(description = "Nombre del curso", example = "Matemáticas I")
        String nombre,

        @Schema(description = "Descripción del curso", example = "Fundamentos matemáticos para nivel básico")
        String descripcion,

        @Schema(description = "Cantidad de créditos del curso", example = "6")
        Integer creditos
) {
}
