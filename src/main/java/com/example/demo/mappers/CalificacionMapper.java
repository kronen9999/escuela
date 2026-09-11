package com.example.demo.mappers;

import com.example.demo.dto.Inscripcion.Datos.InscripcionInscripcionDto;
import com.example.demo.dto.calificacion.CalificacionRequest;
import com.example.demo.dto.calificacion.CalificacionResponse;
import com.example.demo.entities.Calificacion;
import com.example.demo.entities.Inscripcion;
import com.example.demo.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalificacionMapper implements CommonMapper<CalificacionRequest, CalificacionResponse, Calificacion> {

    private final InscripcionMapper inscripcionMapper;

    @Override
    public Calificacion requestAEntidad(CalificacionRequest request) {
        if (request == null) return null;

        return requestAEntidad(request, Inscripcion.builder().id(request.idInscripcion()).build());
    }

    public Calificacion requestAEntidad(CalificacionRequest request, Inscripcion inscripcion) {
        if (request == null) return null;

        return Calificacion.builder()
                .inscripcion(inscripcion)
                .califiacion(request.calificacion())
                .build();
    }

    @Override
    public CalificacionResponse entidadAResponse(Calificacion entidad) {
        if (entidad == null) return null;

        Inscripcion inscripcion = entidad.getInscripcion();

        return new CalificacionResponse(
                entidad.getId(),
                new InscripcionInscripcionDto(
                        inscripcionMapper.entidadAInsAlumnoDto(inscripcion.getAlumno()),
                        inscripcionMapper.entidadAInsGrupoDto(inscripcion.getGrupo()),
                        StringCustomUtils.localeDateAString(inscripcion.getFechaInscripcion())
                ),
                entidad.getCalifiacion(),
                StringCustomUtils.localeDateAString(entidad.getFechaRegistro())
        );
    }
}
