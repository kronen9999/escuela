package com.example.demo.dto.Inscripcion;


import com.example.demo.dto.Inscripcion.Datos.InscripcionAlumnoDto;
import com.example.demo.dto.Inscripcion.Datos.InscripcionGrupoDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Estructura del response de la inscripcion")
public record InscripcionResponse (

        @Schema(description = "Id de la inscripcion",example = "1")
        Long id,

        @Schema(description = "Datos del alumno inscrito")
        InscripcionAlumnoDto alumno,

        @Schema(description = "Datos del grupo al que pertenece la inscripcion")
        InscripcionGrupoDto grupo,

        @Schema(description = "Calificacion de la inscripcion, null si aun no tiene",example = "8.5")
        BigDecimal calificacion,

        @Schema(description = "Fecha en la que se realizo la inscripcion",example = "11/02/2026")
        String fechaInscripcion

) {
}
