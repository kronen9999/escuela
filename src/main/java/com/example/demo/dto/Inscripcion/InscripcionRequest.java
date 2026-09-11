package com.example.demo.dto.Inscripcion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Estructura del request para registrar o actualizar una inscripcion")
public record InscripcionRequest(


        @Schema(description = "Id del alumno",example = "3")
        @NotNull(message = "El id del alumno no puede estar vacio")
        @Positive(message = "El id del alumno debe de ser positivo")
        Long idAlumno,

        @Schema(description = "Id del grupo",example = "6")
        @NotNull(message = "El id del grupo  no puede estar vacio")
        @Positive(message = "El id del grupo debe de ser positivo")
        Long idGrupo
) {}
