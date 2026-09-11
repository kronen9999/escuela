package com.example.demo.dto.alumno;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos nesesarios para insertar o actualizar un alumno")
public record AlumnoRequest(
        @Schema (description = "Nombre del alumno",example = "Alex")
        @NotBlank(message = "El nombre no puede estar vacio")
        @Size(message = "El nombre debe de tener entre 1 y 50 caracteres",min = 1,max = 50)
        String nombre,

        @Schema (description = "Apellido paterno",example = "lopez")
        @NotBlank(message = "El apellido paterno no puede estar vacio")
        @Size(message = "El apellido paterno debe de tener entre 1 y 50 caracteres",min = 1,max = 50)
        String apellidoPaterno,

        @Schema (description = "Apellido materno del alumno",example = "Flores")
        @NotBlank(message = "El apellido materno no puede estar vacio")
        @Size(message = "El apellido materno debe de tener entre 1 y 50 caracteres",min = 1,max = 50)
        String apellidoMaterno

)
{


}
