package com.example.demo.dto.grupo.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información del maestro asociado al grupo")
public record GrupoMaestroDto (

        @Schema(description = "Nombre del maestro",example = "Laura Martínez Martínez")
        String nombre,

        @Schema(description = "Correo electrónico del maestro", example = "laura.martinez@escuela.com")
        String email,

        @Schema(description = "Teléfono del maestro", example = "5551010789")
        String telefono
        ){}
