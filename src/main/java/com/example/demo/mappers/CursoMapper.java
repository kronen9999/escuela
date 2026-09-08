package com.example.demo.mappers;

import com.example.demo.dto.curso.CursoRequest;
import com.example.demo.dto.curso.CursoResponse;
import com.example.demo.dto.datos.DatosCurso;
import com.example.demo.entities.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper implements CommonMapper<CursoRequest, CursoResponse, Curso>{

    @Override
    public Curso requestAEntidad(CursoRequest request) {
        if (request == null) return null;

        return Curso.builder()
                .nombre(normalizarTexto(request.nombre()))
                .descripcion(normalizarTexto(request.descripcion()))
                .creditos(request.creditos())
                .build();
    }

    @Override
    public CursoResponse entidadAResponse(Curso entidad) {
        if (entidad == null) return null;

        return new CursoResponse(
                entidad.getId(),
                normalizarTexto(entidad.getNombre()),
                descripcionODefault(entidad.getDescripcion()),
                entidad.getCreditos());
    }

    public DatosCurso entidadADatosCurso(Curso entidad) {
        if (entidad == null) return null;

        return new DatosCurso(
                normalizarTexto(entidad.getNombre()),
                descripcionODefault(entidad.getDescripcion()),
                entidad.getCreditos());
    }

    private String normalizarTexto(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private String descripcionODefault(String descripcion) {
        return descripcion == null || descripcion.trim().isEmpty() ? "Sin descripcion" : descripcion.trim();
    }
}
