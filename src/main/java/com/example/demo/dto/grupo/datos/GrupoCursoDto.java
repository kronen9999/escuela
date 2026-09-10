package com.example.demo.dto.grupo.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estructura de el objeto curso de cada grupo")
public record GrupoCursoDto(

        @Schema(description = "Nombre del curso",example = "Matemáticas I")
        String nombre,

        @Schema(description = "Descripcion del curso",example = "Introduccion a las matematicas ....")
        String descripcion,

        @Schema(description = "Creditos del curso",example = "9")
        Integer creditos
) {}
