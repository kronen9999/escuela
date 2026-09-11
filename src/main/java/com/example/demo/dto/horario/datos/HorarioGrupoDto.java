package com.example.demo.dto.horario.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información resumida del grupo asociado al horario")
public record HorarioGrupoDto(

        @Schema(description = "Nombre del curso", example = "Matemáticas I")
        String curso,

        @Schema(description = "Nombre completo del maestro", example = "Laura Martínez Martínez")
        String maestro,

        @Schema(description = "Nombre del aula", example = "Aula 101")
        String aula,

        @Schema(description = "Periodo académico del grupo", example = "2026-01")
        String periodo
) { }
