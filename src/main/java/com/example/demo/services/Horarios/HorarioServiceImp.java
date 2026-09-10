package com.example.demo.services.Horarios;


import com.example.demo.dto.horario.HorarioRequest;
import com.example.demo.dto.horario.HorarioResponse;
import com.example.demo.entities.Horario;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.mappers.HorarioMapper;
import com.example.demo.repositories.HorarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class HorarioServiceImp implements HorarioService{

    private final HorarioRepository horarioRepository;

    private final HorarioMapper horarioMapper;
    @Override
    public List<HorarioResponse> listar() {

        log.info("Listando todos los productos");

        return horarioRepository.findAll().stream().map(horarioMapper::entidadAResponse).toList();
    }

    @Override
    public HorarioResponse obtenerPorId(Long id) {

        log.info("Obteniendo horario por id: "+id);

        return horarioMapper.entidadAResponse(
                obtenerHorarioId(id)
        );
    }

    @Override
    public HorarioResponse registrar(HorarioRequest request) {
        return null;
    }

    @Override
    public HorarioResponse actualizar(HorarioRequest request, long id) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }

    private Horario obtenerHorarioId(Long id)
    {

        return horarioRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se pudo encontrar el horario con el id "+ id));

    }

}
