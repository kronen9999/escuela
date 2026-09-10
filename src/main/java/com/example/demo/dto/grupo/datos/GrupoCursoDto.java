package com.example.demo.dto.grupo.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información del curso asociado al grupo")
public record GrupoCursoDto(

        @Schema(description = "Nombre del curso",example = "Matemáticas I")
        String nombre,

        @Schema(description = "Descripción del curso", example = "Fundamentos matemáticos para nivel básico")
        String descripcion,

        @Schema(description = "Créditos del curso", example = "6")
        Integer creditos
) {}
