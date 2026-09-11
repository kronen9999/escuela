package com.example.demo.services.calificaciones;

import com.example.demo.dto.calificacion.CalificacionRequest;
import com.example.demo.dto.calificacion.CalificacionResponse;
import com.example.demo.entities.Calificacion;
import com.example.demo.entities.Inscripcion;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.mappers.CalificacionMapper;
import com.example.demo.repositories.CalificacionRepository;
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
public class CalificacionesServiceImp implements CalificacionService {

    private final CalificacionRepository calificacionRepository;

    private final CalificacionMapper calificacionMapper;

    private final InscripcionRepository inscripcionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CalificacionResponse> listar() {
        log.info("Listando todas las calificaciones");

        return calificacionRepository.findAll().stream().map(calificacionMapper::entidadAResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CalificacionResponse obtenerPorId(Long id) {
        log.info("Obteniendo calificacion por id: {}", id);

        return calificacionMapper.entidadAResponse(obtenerCalificacionPorId(id));
    }

    @Override
    public CalificacionResponse registrar(CalificacionRequest request) {
        log.info("Registrando nueva calificacion");

        Inscripcion inscripcion = obtenerInscripcion(request.idInscripcion());

        validarCalificacionUnica(request.idInscripcion());

        Calificacion calificacion = calificacionMapper.requestAEntidad(request, inscripcion);

        calificacionRepository.save(calificacion);

        log.info("Calificacion con id: {} registrada correctamente", calificacion.getId());

        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public CalificacionResponse actualizar(CalificacionRequest request, long id) {
        log.info("Actualizando calificacion con id: {}", id);

        Calificacion calificacion = obtenerCalificacionPorId(id);

        Inscripcion inscripcion = obtenerInscripcion(request.idInscripcion());

        validarCalificacionUnicaActualizacion(request.idInscripcion(), id);

        calificacion.actualizar(inscripcion, request.calificacion());

        log.info("Calificacion con id: {} actualizada correctamente", id);

        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public void eliminar(Long id) {
        Calificacion calificacion = obtenerCalificacionPorId(id);

        log.info("Eliminando calificacion con id: {}", id);

        calificacionRepository.delete(calificacion);

        log.info("Calificacion con id: {} eliminada correctamente", id);
    }

    private Calificacion obtenerCalificacionPorId(Long id) {
        return calificacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado una calificacion con el id proporcionado: " + id));
    }

    private Inscripcion obtenerInscripcion(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se ha encontrado una inscripcion con el id proporcionado: " + id));
    }

    private void validarCalificacionUnica(Long idInscripcion) {
        if (calificacionRepository.existsByInscripcionId(idInscripcion))
            throw new IllegalArgumentException("La inscripcion ya tiene una calificacion registrada");
    }

    private void validarCalificacionUnicaActualizacion(Long idInscripcion, Long id) {
        if (calificacionRepository.existsByInscripcionIdAndIdNot(idInscripcion, id))
            throw new IllegalArgumentException("Ya existe otra calificacion registrada para esta inscripcion");
    }
}
