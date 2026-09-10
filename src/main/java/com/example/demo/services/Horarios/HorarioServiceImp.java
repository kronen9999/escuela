package com.example.demo.services.Horarios;


import com.example.demo.dto.horario.HorarioRequest;
import com.example.demo.dto.horario.HorarioResponse;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Horario;
import com.example.demo.enums.DiaSemana;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.mappers.HorarioMapper;
import com.example.demo.repositories.GrupoRepository;
import com.example.demo.repositories.HorarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class HorarioServiceImp implements HorarioService{

    private final HorarioRepository horarioRepository;

    private final HorarioMapper horarioMapper;

    private final GrupoRepository grupoRepository;
    @Transactional(readOnly = true)
    @Override
    public List<HorarioResponse> listar() {

        log.info("Listando todos los horarios");

        return horarioRepository.findAll().stream().map(horarioMapper::entidadAResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public HorarioResponse obtenerPorId(Long id) {

        log.info("Obteniendo horario por id: "+id);

        return horarioMapper.entidadAResponse(
                obtenerHorarioId(id)
        );
    }

    @Override
    public HorarioResponse registrar(HorarioRequest request) {

        log.info("Registrando horario");

        DiaSemana diaSemana = obtenerDiaSemana(request.dia());
        validarRangoHoras(request.horaInicio(), request.horaFin());
        Grupo grupo = obtenerGrupo(request.idGrupo());
        validarTraslapeEnGrupo(grupo, diaSemana, request);
        validarTraslapeEnAula(grupo, diaSemana, request);

        Horario horario = horarioMapper.requestAEntidad(request, grupo);
        horarioRepository.save(horario);

        log.info("Horario registrado correctamente con id: {}", horario.getId());
        return horarioMapper.entidadAResponse(horario);
    }

    @Override
    public HorarioResponse actualizar(HorarioRequest request, long id) {
        log.info("Actualizando horario con id: {}", id);

        Horario horario = obtenerHorarioId(id);
        DiaSemana diaSemana = obtenerDiaSemana(request.dia());

        validarRangoHoras(request.horaInicio(), request.horaFin());

        Grupo grupo = obtenerGrupo(request.idGrupo());

        validarTraslapeEnGrupoExcepto(horario, grupo, diaSemana, request);
        validarTraslapeEnAulaExcepto(horario, grupo, diaSemana, request);

        horario.actualizar(
                grupo,
                diaSemana,
                request.horaInicio(),
                request.horaFin()
        );

        log.info("Horario actualizado correctamente con id: {}", id);
        return horarioMapper.entidadAResponse(horario);
    }

    @Override
    public void eliminar(Long id) {

        log.info("Eliminando horario con el id {}",id);

        Horario horario= obtenerHorarioId(id);

        horarioRepository.delete(horario);

    }

    private Horario obtenerHorarioId(Long id)
    {

        return horarioRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se pudo encontrar el horario con el id "+ id));

    }


    private void validarRangoHoras(String horaInicio,String horaFin)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime inicio = LocalTime.parse(horaInicio, formatter);
        LocalTime fin = LocalTime.parse(horaFin, formatter);

        if (!fin.isAfter(inicio)) {
            throw new IllegalArgumentException(
                    "La hora de fin debe ser posterior a la hora de inicio"
            );
        }
    }

    private DiaSemana obtenerDiaSemana(String dia) {
            try {
                return DiaSemana.obtenerDiaSemanaPorDescripcion(dia);
            } catch (RecursoNoEncontradoException exception) {
                throw new IllegalArgumentException(exception.getMessage());
            }
        }

        private void validarTraslapeEnGrupo(
                Grupo grupo,
                DiaSemana dia,
                HorarioRequest request
        ) {
            if (horarioRepository.existeTraslapeEnGrupo(
                    grupo.getId(),
                    dia,
                    request.horaInicio(),
                    request.horaFin()
            )) {
                throw new IllegalArgumentException(
                        "El horario se traslapa con otro horario del grupo"
                );
            }
        }

        private void validarTraslapeEnGrupoExcepto(
                Horario horario,
                Grupo grupo,
                DiaSemana dia,
                HorarioRequest request
        ) {
            if (horarioRepository.existeTraslapeEnGrupoExcepto(
                    horario.getId(),
                    grupo.getId(),
                    dia,
                    request.horaInicio(),
                    request.horaFin()
            )) {
                throw new IllegalArgumentException(
                        "El horario se traslapa con otro horario del grupo"
                );
            }
        }

        private void validarTraslapeEnAula(
                Grupo grupo,
                DiaSemana dia,
                HorarioRequest request
        ) {
            if (horarioRepository.existeTraslapeEnAula(
                    grupo.getAula().getId(),
                    grupo.getPeriodo(),
                    dia,
                    request.horaInicio(),
                    request.horaFin()
            )) {
                throw new IllegalArgumentException(
                        "El horario se traslapa con otro horario de la misma aula"
                );
            }
        }

        private void validarTraslapeEnAulaExcepto(
                Horario horario,
                Grupo grupo,
                DiaSemana dia,
                HorarioRequest request
        ) {
            if (horarioRepository.existeTraslapeEnAulaExcepto(
                    horario.getId(),
                    grupo.getAula().getId(),
                    grupo.getPeriodo(),
                    dia,
                    request.horaInicio(),
                    request.horaFin()
            )) {
                throw new IllegalArgumentException(
                        "El horario se traslapa con otro horario de la misma aula"
                );
            }
    }

    private Grupo obtenerGrupo(Long id)
    {
        return grupoRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se encontro el grupo con el id proporcionado:"+id));
    }






}
