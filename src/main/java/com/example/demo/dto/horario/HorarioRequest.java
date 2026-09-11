package com.example.demo.dto.horario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Schema(description = "Formato del request para  registrar o actualizar un horario")
public record HorarioRequest(

        @Schema(description = "Identificador del grupo al que pertenece el horario", example = "1")
        @NotNull(message = "El id del grupo es obligatorio")
        @Positive(message = "El id del grupo debe ser positivo")
        Long idGrupo,

        @Schema(description = "Día de la semana del horario", example = "Lunes")
        @NotBlank(message = "El día es obligatorio")
        String dia,

        @Schema(description = "Hora de inicio del horario en formato HH:mm", example = "08:00")
        @NotBlank(message = "La hora de inicio es obligatoria")
        @Pattern(
                regexp = "^([01]\\d|2[0-3]):[0-5]\\d$",
                message = "La hora de inicio debe cumplir con el formato HH:mm"
        )
        String horaInicio,

        @Schema(description = "Hora de fin del horario en formato HH:mm", example = "10:00")
        @NotBlank(message = "La hora de fin es obligatoria")
        @Pattern(
                regexp = "^([01]\\d|2[0-3]):[0-5]\\d$",
                message = "La hora de fin debe cumplir con el formato HH:mm"
        )
        String horaFin
) {
}
