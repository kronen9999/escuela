package com.example.demo.dto.alumno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AlumnoResponse(

        Long id ,

        String nombre,

        String apellidoMaterno,

        String apellidoPaterno,

        String email,

        String matricula,

        LocalDate fecha

) {}
