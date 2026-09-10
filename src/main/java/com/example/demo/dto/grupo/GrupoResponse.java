package com.example.demo.dto.grupo;

import com.example.demo.dto.grupo.datos.GrupoAulaDto;
import com.example.demo.dto.grupo.datos.GrupoCursoDto;
import com.example.demo.dto.grupo.datos.GrupoMaestroDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Campos que debe de contener el response de grupo")
public record GrupoResponse(

        @Schema(description = "Id del grupo",example = "1")
        Long idGrupo,

        @Schema(description = "Objeto curso")
        GrupoCursoDto curso,

        @Schema(description = "Objeto maestro")
        GrupoMaestroDto maestro,

        @Schema(description = "Objeto aula")
        GrupoAulaDto aula,

        @Schema(description = "Lista de horarios")
        List<String> horarios,

        @Schema(description = "Periodo del grupo",example = "2026-01")
        String periodo






) {}
