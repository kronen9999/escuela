package com.example.demo.dto.grupo.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estructura de el objeto aula de cada grupo")
public record GrupoAulaDto (

        @Schema(description = "Nombre del aula",example = "Aula 101")
        String nombre,

        @Schema(description = "Capacidad del aula",example = "30")
        Integer capacidad

){ }
