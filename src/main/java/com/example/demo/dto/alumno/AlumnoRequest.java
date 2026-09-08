package com.example.demo.dto.alumno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record AlumnoRequest(
        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,

        @NotBlank(message = "El apellido materno no puede estar vacio")
        String apellidoMaterno,

        @NotBlank(message = "El apellido paterno no puede estar vacio")
        String apellidoPaterno,

        @NotBlank(message = "El email no puede quedar vacio")
        @Email(message = "El email debe de terner un formato valido")
        String email,

        @NotBlank(message = "La matricula no debe de estar vacia")
        @Size(message = "La matricula debe de tener 10 caracteres",min = 10,max = 10)
        String matricula,

        LocalDate fecha)
{


}
