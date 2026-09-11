package com.example.demo.services.alumnos;

import com.example.demo.dto.alumno.AlumnoRequest;
import com.example.demo.dto.alumno.AlumnoResponse;
import com.example.demo.entities.Alumno;
import com.example.demo.exceptions.EntidadRelacionadaException;
import com.example.demo.mappers.AlumnoMapper;
import com.example.demo.repositories.AlumnoRepository;
import com.example.demo.repositories.InscripcionRepository;
import com.example.demo.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AlumnosServiceImp implements AlumnoService{

    private final AlumnoMapper alumnoMapper;

    private  final InscripcionRepository inscripcionRepository;

    private final AlumnoRepository alumnoRepository;


    @Transactional(readOnly = true)
    @Override
    public List<AlumnoResponse> listar() {
        log.info("Listando todos los alumnos");

        return alumnoRepository.findAll().stream().map(alumnoMapper::entidadAResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public AlumnoResponse obtenerPorId(Long id) {
     return alumnoMapper.entidadAResponse(obtenerAlumno(id));
    }

    @Override
    public AlumnoResponse registrar(AlumnoRequest request) {

        log.info("Registrando nuevo alumno...");

        Alumno alumno = alumnoMapper.requestAEntidad(request,generarEmail(request),generarMatricula(request));

        alumnoRepository.save(alumno);

        log.info("Nuevo alumno {}  registrado correctamente");

        return alumnoMapper.entidadAResponse(alumno);
    }

    @Override
    public AlumnoResponse actualizar(AlumnoRequest request, long id) {

        Alumno alumno = obtenerAlumno(id);
        log.info("Actualizando alumno con id: {}",id);

        if (alumno.cambioEnDatos(request.nombre().trim(),request.apellidoPaterno().trim(),request.apellidoMaterno().trim()))
        {
            alumno.actualizar(request.nombre(),request.apellidoPaterno(),request.apellidoMaterno(),generarEmail(request),generarMatricula(request));

            log.info("Datos academicos generados para el alumno con id: {}",id);
        }

        return alumnoMapper.entidadAResponse(alumno);

    }

    @Override
    public void eliminar(Long id) {

        Alumno alumno = obtenerAlumno(id);

        log.info("Eliminando alumno con id : {} ",id);

        if (inscripcionRepository.existsByAlumnoId(id))
            throw  new EntidadRelacionadaException("No se puede eliminar el alumno ya que tiene inscripciones asignadas");

        log.info("Alumno con id: {} eliminando ",id);

        alumnoRepository.delete(alumno);



    }

    private Alumno obtenerAlumno (Long id)
    {
        return ServiceUtils.obtenerEntidadOException(alumnoRepository,id, Alumno.class);
    }

    private String generarEmail(AlumnoRequest request)
    {
        log.info("Generando email...");

        return alumnoRepository.generarEmail(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim()
        );
    }

    private String generarMatricula(AlumnoRequest request)
    {
        log.info("Generando matricula...");

        return alumnoRepository.generarMatricula(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim()
        );
    }



}
