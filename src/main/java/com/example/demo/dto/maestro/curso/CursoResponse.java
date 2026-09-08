package com.example.demo.dto.maestro.curso;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoResponse(

        Long id,

        String nombre,

        String descripcion,

        Short creditos
) {
}
