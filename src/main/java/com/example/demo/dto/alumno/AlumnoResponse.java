package com.example.demo.dto.alumno;

import com.example.demo.dto.datos.DatosCalificacion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Descripcion del alumno")
public record AlumnoResponse(


        @Schema(description = "Id del maestro",example = "1")
        Long id,

        @Schema(description = "Nombre completo del alumno",example = "Maximo Decimo Meridio")
        String nombre,

        @Schema(description = "Email del alumno",example = "test@test.com")
        String email,

        @Schema(description = "Matricula del alumno",example = "1234567890")
        String matricula,

        @Schema(description = "Fecha ingreso del alumno",example = "12/09/2026")
        String fechaIngreso,

        @Schema(description = "Datos de calificaciones del alumno")
        List <DatosCalificacion> calificaciones,


        @Schema(description = "Promedio del alumno",example ="9.9")
        BigDecimal promedio

) {}
