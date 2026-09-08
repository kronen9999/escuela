package com.example.demo.mappers;

import com.example.demo.dto.curso.CursoRequest;
import com.example.demo.dto.curso.CursoResponse;
import com.example.demo.dto.datos.DatosCurso;
import com.example.demo.entities.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper implements CommonMapper<CursoRequest, CursoResponse, Curso>{
    @Override
    public Curso requestAEntiddad(CursoRequest request) {
        if (request==null)return null;

        return Curso.builder()
                .nombre(request.nombre().trim())
                .descripcion(request.descripcion())
                .creditos(request.creditos())
                .build();
    }

    @Override
    public CursoResponse entidadAResponse(Curso entidad) {
        if (entidad==null) return  null;

        return  new CursoResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDescripcion()!= null?
                        entidad.getDescripcion():"Sin descripcion",
                entidad.getCreditos());
    }

    public DatosCurso entidadADatosCurso(Curso entidad) {
        if (entidad==null) return  null;

        return  new DatosCurso(
                entidad.getNombre(),
                entidad.getDescripcion()!= null?
                        entidad.getDescripcion():"Sin descripcion",
                entidad.getCreditos());
    }
}
