package com.example.demo.dto.grupo;

import com.example.demo.dto.grupo.datos.GrupoAulaDto;
import com.example.demo.dto.grupo.datos.GrupoCursoDto;
import com.example.demo.dto.grupo.datos.GrupoMaestroDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Respuesta con la información completa de un grupo académico")
public record GrupoResponse(

        @Schema(description = "Identificador del grupo", example = "1")
        Long id,

        @Schema(description = "Curso impartido por el grupo")
        GrupoCursoDto curso,

        @Schema(description = "Maestro asignado al grupo")
        GrupoMaestroDto maestro,

        @Schema(description = "Aula asignada al grupo")
        GrupoAulaDto aula,

        @Schema(description = "Horarios asociados al grupo",
                example = "[\"Lunes 08:00 - 10:00\", \"Miércoles 08:00 - 10:00\"]")
        List<String> horarios,

        @Schema(description = "Periodo del grupo",example = "2026-01")
        String periodo






) {}
