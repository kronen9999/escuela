package com.example.demo.dto.maestro;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record MaestroRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "EL apellido paterno es obligatorio")
        String apellidoPaterno,
        @NotBlank(message = "El apellido materno es obligatorio")
        String apeliidoMaterno,
        @NotBlank(message = "El email es obligatorio")
        String email,
        @NotBlank(message = "El telefono es obligatorio")
        String telefono
) {
}
