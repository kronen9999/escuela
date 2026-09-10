package com.example.demo.mappers;


import com.example.demo.dto.grupo.GrupoRequest;
import com.example.demo.dto.grupo.GrupoResponse;
import com.example.demo.dto.grupo.datos.GrupoAulaDto;
import com.example.demo.dto.grupo.datos.GrupoCursoDto;
import com.example.demo.dto.grupo.datos.GrupoMaestroDto;
import com.example.demo.entities.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class GrupoMapper implements CommonMapper<GrupoRequest, GrupoResponse, Grupo> {

    @Override
    public Grupo requestAEntidad(GrupoRequest request) {

        return requestAEntidad(
                request,
                Curso.builder().id(request.idCurso()).build(),
                Maestro.builder().id(request.idMaestro()).build(),
                Aula.builder().id(request.idAula()).build()
        );
    }


    public Grupo requestAEntidad(
            GrupoRequest request,
            Curso curso,
            Maestro maestro,
            Aula aula
    ) {

        return Grupo.builder()
                .curso(curso)
                .maestro(maestro)
                .aula(aula)
                .periodo(request.periodo())
                .build();

    }

    @Override
    public GrupoResponse entidadAResponse(Grupo grupo) {
        if (grupo == null) {
            return null;
        }

        List<String> horarios = grupo.getHorarios() == null
                ? List.of()
                : grupo.getHorarios().stream()
                        .filter(Objects::nonNull)
                        .map(this::horarioAString)
                        .toList();

        return new GrupoResponse(
                grupo.getId(),
                cursoAGrupoCurso(grupo.getCurso()),
                maestroAGrupoMaestro(grupo.getMaestro()),
                aulaAGrupoAula(grupo.getAula()),
                horarios,
                grupo.getPeriodo()

        );
    }

    private GrupoCursoDto cursoAGrupoCurso(Curso curso)
    {
        if (curso == null) {
            return null;
        }

        return new GrupoCursoDto(
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getCreditos()
        );

    }

    private GrupoMaestroDto maestroAGrupoMaestro(Maestro maestro)
    {
        if (maestro == null) {
            return null;
        }

        return new GrupoMaestroDto(
                String.join(" ",
                        maestro.getNombre(),maestro.getApellidoPaterno(),maestro.getApellidoMaterno()),
                maestro.getEmail(),
                maestro.getTelefono()
        );

    }

    private GrupoAulaDto aulaAGrupoAula(Aula aula)
    {
        if (aula == null) {
            return null;
        }

        return new GrupoAulaDto(
                aula.getNombre(),
                aula.getCapacidad()
        );
    }

    private String horarioAString(Horario horario){

        return horario.getDiaSemana().getDescripcion()
                + " " + horario.getHoraInicio()
                + " - " + horario.getHorFIn();

    }


}
