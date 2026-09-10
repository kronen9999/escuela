package com.example.demo.dto.grupo;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para registrar o actualizar un grupo")
public record GrupoRequest
        (
                @NotNull(message = "El id del curso es obligatorio")
                @Schema(description = "Id del curso",example = "4")
                Long idCurso,

                @NotNull(message = "El id del maestro es obligatorio")
                @Schema(description = "Id del maestro",example = "2")
                Long idMaestro,

                @NotNull(message = "El id del aula es obligatorio")
                @Schema(description = "Id del aula",example = "8")
                Long idAula,

                @Schema(description = "Periodo del grupo",example = "2026-01")
                @Size(min = 7,max = 20,message = "Los caracteres de la cadena deben de ir entre 7 y 20")
                @Pattern(regexp ="^\\d{4}-(0[1-9]|1[0-2])$" ,message = "El periodo no cumple con el formato YYYY-MM")
                @NotBlank(message = "El periodo es obligatorio")
                String periodo

        ){}
