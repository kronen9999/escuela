package com.example.demo.dto.horario;


import com.example.demo.dto.horario.datos.HorarioGrupoDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con la información de un horario")
public record HorarioResponse(

        @Schema(description = "Identificador del horario", example = "1")
        Long id,

        @Schema(description = "Información resumida del grupo asociado")
        HorarioGrupoDto grupo,

        @Schema(description = "Horario en formato día HH:mm - HH:mm",
                example = "Lunes 08:00 - 10:00")
        String horario
) {
}
