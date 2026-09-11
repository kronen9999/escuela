package com.example.demo.services.Inscripciones;

import com.example.demo.dto.Inscripcion.InscripcionRequest;
import com.example.demo.dto.Inscripcion.InscripcionResponse;
import com.example.demo.entities.Alumno;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Inscripcion;
import com.example.demo.exceptions.ConflictException;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.mappers.InscripcionMapper;
import com.example.demo.repositories.AlumnoRepository;
import com.example.demo.repositories.CalificacionRepository;
import com.example.demo.repositories.GrupoRepository;
import com.example.demo.repositories.InscripcionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class InscripcionesServiceImp implements  InscripcionesService{

    private final InscripcionRepository inscripcionRepository;

    private final InscripcionMapper inscripcionMapper;

    private final AlumnoRepository alumnoRepository;

    private final GrupoRepository grupoRepository;

    private final CalificacionRepository calificacionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InscripcionResponse> listar() {
        log.info("Listando todas las Inscripciones");

        return inscripcionRepository.findAll().stream().map(inscripcionMapper::entidadAResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public InscripcionResponse obtenerPorId(Long id) {

        log.info("Obteniendo inscripcion por id " +id);

        Inscripcion inscripcion=obtenerInscripcionPorId(id);

        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public InscripcionResponse registrar(InscripcionRequest request) {

        log.info("Registrando nueva inscripcion");

        Alumno alumno = obtenerAlumno(request.idAlumno());

        Grupo grupo = obtenerGrupo(request.idGrupo());

        validarInscripcionUnica(request.idAlumno(), request.idGrupo());

        Inscripcion inscripcion = inscripcionMapper.requestAEntidad(request, alumno, grupo);

        inscripcionRepository.save(inscripcion);

        log.info("Inscripcion con id: {} registrada correctamente", inscripcion.getId());

        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public InscripcionResponse actualizar(InscripcionRequest request, long id) {

        log.info("Actualizando inscripcion con id: {}", id);

        Inscripcion inscripcion = obtenerInscripcionPorId(id);

        Alumno alumno = obtenerAlumno(request.idAlumno());

        Grupo grupo = obtenerGrupo(request.idGrupo());

        validarInscripcionUnicaActualizacion(request.idAlumno(), request.idGrupo(), id);

        inscripcion.actualizar(alumno, grupo);

        log.info("Inscripcion con id: {} actualizada correctamente", id);

        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public void eliminar(Long id) {

        Inscripcion inscripcion = obtenerInscripcionPorId(id);

        log.info("Eliminando inscripcion con id: {}", id);

        validarInscripcionSinCalificacion(id);

        inscripcionRepository.delete(inscripcion);

        log.info("Inscripcion con id: {} eliminada correctamente", id);
    }

    private Inscripcion obtenerInscripcionPorId(Long id)
    {

        return inscripcionRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se ha encontrado una inscripcion con el id proporcionado: "+id));

    }

    private Alumno obtenerAlumno(Long id)
    {

        return alumnoRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se ha encontrado un alumno con el id proporcionado: "+id));

    }

    private Grupo obtenerGrupo(Long id)
    {

        return grupoRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se ha encontrado un grupo con el id proporcionado: "+id));

    }

    private void validarInscripcionUnica(Long idAlumno, Long idGrupo)
    {

        if (inscripcionRepository.existsByAlumnoIdAndGrupoId(idAlumno, idGrupo))
            throw new IllegalArgumentException("El alumno ya se encuentra inscrito en este grupo");

    }

    private void validarInscripcionUnicaActualizacion(Long idAlumno, Long idGrupo, Long id)
    {

        if (inscripcionRepository.existsByAlumnoIdAndGrupoIdAndIdNot(idAlumno, idGrupo, id))
            throw new IllegalArgumentException("Ya existe otra inscripcion con el mismo alumno y grupo");

    }

    private void validarInscripcionSinCalificacion(Long id)
    {

        if (calificacionRepository.existsByInscripcionId(id))
            throw new ConflictException("No se puede eliminar la inscripcion porque tiene una calificacion asociada");

    }
}
