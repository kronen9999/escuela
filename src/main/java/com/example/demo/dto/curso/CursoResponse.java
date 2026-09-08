package com.example.demo.dto.curso;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Informacion del curso")
public record CursoResponse(

        @Schema(description = "Id del curso ",example = "1")
        Long id,

        @Schema(description = "Nombre del curso",example = "Matematicas 1")
        String nombre,

        @Schema(description = "Descripcion del curso",example = "Introduccion a las matematicas para.....")
        String descripcion,

        @Schema(description = "Creditos del curso ",example ="5")
        Integer creditos
) {
}
