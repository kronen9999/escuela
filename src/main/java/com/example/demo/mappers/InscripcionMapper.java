package com.example.demo.mappers;

import com.example.demo.dto.Inscripcion.Datos.InscripcionAlumnoDto;
import com.example.demo.dto.Inscripcion.Datos.InscripcionGrupoDto;
import com.example.demo.dto.Inscripcion.InscripcionRequest;
import com.example.demo.dto.Inscripcion.InscripcionResponse;
import com.example.demo.entities.Alumno;
import com.example.demo.entities.Calificacion;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Inscripcion;
import com.example.demo.utils.StringCustomUtils;
import org.springframework.stereotype.Component;

@Component
public class InscripcionMapper implements CommonMapper<InscripcionRequest, InscripcionResponse, Inscripcion> {

    @Override
    public Inscripcion requestAEntidad(InscripcionRequest request) {
        if (request == null) return null;

        return requestAEntidad(
                request,
                Alumno.builder().id(request.idAlumno()).build(),
                Grupo.builder().id(request.idGrupo()).build()
        );
    }

    public Inscripcion requestAEntidad(InscripcionRequest request, Alumno alumno, Grupo grupo) {
        if (request == null) return null;

        return Inscripcion.builder()
                .alumno(alumno)
                .grupo(grupo)
                .build();
    }

    @Override
    public InscripcionResponse entidadAResponse(Inscripcion entidad) {
        if (entidad == null) return null;

        Calificacion calificacion = entidad.getCaalificacion();

        return new InscripcionResponse(
                entidad.getId(),
                entidadAInsAlumnoDto(entidad.getAlumno()),
                entidadAInsGrupoDto(entidad.getGrupo()),
                calificacion == null ? null : calificacion.getCalifiacion(),
                StringCustomUtils.localeDateAString(entidad.getFechaInscripcion())
        );
    }

    public InscripcionAlumnoDto entidadAInsAlumnoDto(Alumno alumno) {
        if (alumno == null) return null;

        return new InscripcionAlumnoDto(
                String.join(" ",
                        alumno.getNombre(),
                        alumno.getApellidoPaterno(),
                        alumno.getApellidoMaterno()),
                alumno.getMatricula(),
                alumno.getEmail(),
                StringCustomUtils.localeDateAString(alumno.getFechaIngreso())
        );
    }

    public InscripcionGrupoDto entidadAInsGrupoDto(Grupo grupo) {
        if (grupo == null) return null;

        return new InscripcionGrupoDto(
                grupo.getCurso().getNombre(),
                String.join(" ",
                        grupo.getMaestro().getNombre(),
                        grupo.getMaestro().getApellidoPaterno(),
                        grupo.getMaestro().getApellidoMaterno()),
                grupo.getAula().getNombre(),
                grupo.getPeriodo()
        );
    }
}
