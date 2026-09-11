package com.example.demo.mappers;

import com.example.demo.dto.horario.HorarioRequest;
import com.example.demo.dto.horario.HorarioResponse;
import com.example.demo.dto.horario.datos.HorarioGrupoDto;
import com.example.demo.entities.Aula;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Horario;
import com.example.demo.entities.Maestro;
import com.example.demo.enums.DiaSemana;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper implements CommonMapper<HorarioRequest, HorarioResponse, Horario> {

    @Override
    public Horario requestAEntidad(HorarioRequest request) {
        if (request == null) {
            return null;
        }

        return requestAEntidad(
                request,
                Grupo.builder().id(request.idGrupo()).build()
        );
    }

    public Horario requestAEntidad(HorarioRequest request, Grupo grupo) {
        if (request == null) {
            return null;
        }

        return Horario.builder()
                .grupo(grupo)
                .diaSemana(DiaSemana.obtenerDiaSemanaPorDescripcion(request.dia()))
                .horaInicio(request.horaInicio())
                .horaFIn(request.horaFin())
                .build();
    }

    @Override
    public HorarioResponse entidadAResponse(Horario entidad) {
        if (entidad == null) {
            return null;
        }

        return new HorarioResponse(
                entidad.getId(),
                grupoAHorarioGrupoDto(entidad.getGrupo()),
                horarioAString(entidad)
        );
    }

    private HorarioGrupoDto grupoAHorarioGrupoDto(Grupo grupo) {
        if (grupo == null) {
            return null;
        }

        Curso curso = grupo.getCurso();
        Maestro maestro = grupo.getMaestro();
        Aula aula = grupo.getAula();

        String nombreMaestro = maestro == null
                ? null
                : String.join(
                        " ",
                        maestro.getNombre(),
                        maestro.getApellidoPaterno(),
                        maestro.getApellidoMaterno()
                );

        return new HorarioGrupoDto(
                curso == null ? null : curso.getNombre(),
                nombreMaestro,
                aula == null ? null : aula.getNombre(),
                grupo.getPeriodo()
        );
    }

    private String horarioAString(Horario horario) {
        if (horario.getDiaSemana() == null
                || horario.getHoraInicio() == null
                || horario.getHoraFIn() == null) {
            return null;
        }

        return horario.getDiaSemana().getDescripcion()
                + " " + horario.getHoraInicio()
                + " - " + horario.getHoraFIn();
    }
}
