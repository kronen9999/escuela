package com.example.demo.dto.Inscripcion.Datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos resumidos del alumno dentro de una inscripcion")
public record InscripcionAlumnoDto(

        @Schema(description = "Nombre completo del alumno",example = "Juan Perez Lopez")
        String nombre,

        @Schema(description = "Matricula del alumno",example = "A2025001")
        String matricula,

        @Schema(description = "Email institucional del alumno",example = "juan.perez@alumnos.com")
        String email,

        @Schema(description = "Fecha de ingreso del alumno",example = "10/01/2025")
        String fechaIngreso

) { }
