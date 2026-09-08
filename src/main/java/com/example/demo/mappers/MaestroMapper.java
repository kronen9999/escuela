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
    public Maestro requestAEntiddad(MaestroRequest request) {
        if (request==null) return null;

        return Maestro.builder().nombre(
                request.nombre().trim())
                .apellidoPaterno(request.apellidoPaterno().trim())
                .apellidoMaterno(request.apellidoMaterno().trim())
                .email(request.email().trim())
                .telefono(request.telefono().trim())
                .build();
    }

    @Override
    public MaestroResponse entidadAResponse(Maestro entidad) {
        if (entidad==null) return null;

        List<DatosCurso> cursos=entidadADatoCurso(entidad);

        return new MaestroResponse(entidad.getId(),String.join(
                " ",entidad.getNombre()
                , entidad.getApellidoPaterno()
                ,entidad.getApellidoMaterno()
                ),entidad.getEmail(),
                entidad.getTelefono(),
                cursos);

    }


    private List <DatosCurso> entidadADatoCurso(Maestro entidad) {
        if (entidad==null) return List.of();



        return entidad.getGrupos().stream().map(Grupo::getCurso).map(cursoMapper::entidadADatosCurso).toList();

    }


}
