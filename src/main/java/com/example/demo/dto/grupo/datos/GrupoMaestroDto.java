package com.example.demo.dto.grupo.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estructura de el objeto maestro de cada grupo")
public record GrupoMaestroDto (

        @Schema(description = "Nombre del maestro",example = "Laura Martínez Martínez")
        String nombre,

        @Schema(description = "Correo del maestro",example = "laura.martinez@escuela.com")
        String email,

        @Schema(description = "Telefono del maestro",example = "5551010789")
        String telefono
        ){}
