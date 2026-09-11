package com.example.demo.dto.curso;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;


@Schema(description = "Datos necesarios para registrar o actualizar un curso")
public record CursoRequest(

        @Schema(description = "Nombre del curso", example = "Matemáticas I")
        @NotBlank(message = "El nombre es requerido")
        @Size(min=5,max = 100,message = "El nombre debe tener entre 5 y 100 caracteres")
        String nombre,

        @Schema(description = "Descripción del curso", example = "Fundamentos matemáticos para nivel básico")
        @Size(max = 200,message = "La descripción debe tener máximo 200 caracteres")
        String descripcion,

        @Schema(description = "Cantidad de créditos del curso", example = "6")
        @NotNull(message = "Los creditos son requeridos")
        @Min(value = 1,message = "Los creditos minimos son 1")
        @Max(value = 10,message = "Los creditos maximos son 10")
        Integer creditos



){}
