package com.example.demo.dto.Inscripcion.Datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos resumidos del grupo dentro de una inscripcion")
public record InscripcionGrupoDto(

        @Schema(description = "Nombre del curso",example = "Matematicas I")
        String curso,

        @Schema(description = "Nombre completo del maestro",example = "Laura Martinez Martinez")
        String maestro,

        @Schema(description = "Nombre del aula",example = "Aula 101")
        String aula,

        @Schema(description = "Periodo del grupo",example = "2025-1")
        String periodo

) { }
