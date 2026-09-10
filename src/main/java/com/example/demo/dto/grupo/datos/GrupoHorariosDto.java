package com.example.demo.dto.grupo.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estructura interna de la lista de horarios")
public record GrupoHorariosDto(
        @Schema(description = "Horario en formato dia hora inicio- hora fin",example = "Lunes 08:00 - 10:00")
        String horario
) {}
