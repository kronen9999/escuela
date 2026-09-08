package com.example.demo.dto.maestros;

import com.example.demo.dto.datos.DatosCurso;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Informacion de un maestro")
public record MaestroResponse(

        @Schema(description = "Id del maestro",example = "1")
        Long id,

        @Schema(description = "Nombre completo del maestro",example = "Maximo Decimo Meridio")
        String nombre,

        @Schema(description = "Email del maestro",example = "test@test.com")
        String email,

        @Schema(description = "Telefono del maestro",example = "1234567890")
        String telefono,

        @Schema(description = "Datos de los cursos del maestro")
        List<DatosCurso> cursos

) {
}
