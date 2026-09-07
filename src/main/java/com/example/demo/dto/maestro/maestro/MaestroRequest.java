package com.example.demo.dto.maestro.maestro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MaestroRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "EL apellido paterno es obligatorio")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno es obligatorio")
        String apeliidoMaterno,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe de tener el formato adecuado")
        String email,

        @NotBlank(message = "El telefono es obligatorio")
        @Size(message = "El numero de telefono debe de tener 10 caracteres",min =10,max =10 )
        String telefono
) {
}
