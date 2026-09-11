package com.example.demo.mappers;

import com.example.demo.dto.datos.DatosCurso;
import com.example.demo.dto.maestros.MaestroRequest;
import com.example.demo.dto.maestros.MaestroResponse;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Maestro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MaestroMapper implements  CommonMapper<MaestroRequest, MaestroResponse, Maestro> {



    private final CursoMapper cursoMapper;

    @Override
    public Maestro requestAEntidad(MaestroRequest request) {
        if (request == null) return null;

        return Maestro.builder()
                .nombre(normalizarTexto(request.nombre()))
                .apellidoPaterno(normalizarTexto(request.apellidoPaterno()))
                .apellidoMaterno(normalizarTexto(request.apellidoMaterno()))
                .email(normalizarTexto(request.email()))
                .telefono(normalizarTexto(request.telefono()))
                .build();
    }


    @Override
    public MaestroResponse entidadAResponse(Maestro entidad) {
        if (entidad == null) return null;

        List<DatosCurso> cursos = entidadADatoCurso(entidad);

        return new MaestroResponse(
                entidad.getId(),
                String.join(" ",
                        normalizarTexto(entidad.getNombre()),
                        normalizarTexto(entidad.getApellidoPaterno()),
                        normalizarTexto(entidad.getApellidoMaterno())),
                normalizarTexto(entidad.getEmail()),
                normalizarTexto(entidad.getTelefono()),
                cursos
        );
    }

    private List<DatosCurso> entidadADatoCurso(Maestro entidad) {
        if (entidad == null || entidad.getGrupos() == null) return List.of();

        return entidad.getGrupos().stream()
                .filter(grupo -> grupo != null && grupo.getCurso() != null)
                .map(Grupo::getCurso)
                .map(cursoMapper::entidadADatosCurso)
                .filter(curso -> curso != null)
                .toList();
    }

    private String normalizarTexto(String valor) {
        return valor == null ? "" : valor.trim();
    }

}
