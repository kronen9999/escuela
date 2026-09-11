package com.example.demo.dto.Inscripcion.Datos;

import io.swagger.v3.oas.annotations.media.Schema;

public record InscripcionInscripcionDto(
        @Schema(description = "Datos del alumno inscrito")
        InscripcionAlumnoDto alumno,

        @Schema(description = "Datos del grupo al que pertenece la inscripcion")
        InscripcionGrupoDto grupo,

        @Schema(description = "Fecha en que se realizo la inscripcion",example = "11/02/2026")
        String fechaInscripcion
) {



}
