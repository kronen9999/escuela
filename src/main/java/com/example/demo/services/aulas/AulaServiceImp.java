package com.example.demo.services.aulas;

import com.example.demo.dto.aula.AulaRequest;
import com.example.demo.dto.aula.AulaResponse;
import com.example.demo.entities.Aula;
import com.example.demo.exceptions.ConflictException;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.mappers.AulaMapper;
import com.example.demo.repositories.AulaRepository;
import com.example.demo.repositories.GrupoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
@Transactional
public class AulaServiceImp implements AulaService {

    private final AulaRepository aulaRepository;

    private final AulaMapper aulaMapper;

    private  final GrupoRepository grupoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AulaResponse> listar() {

        log.info("Listando total de aulas");

        return aulaRepository.findAll().stream().map(aulaMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AulaResponse obtenerPorId(Long id) {

        log.info("Obteniendo aula por id: {}",id);

     return aulaMapper.entidadAResponse(obtenerAulaPorId(id));

    }

    @Override
    public AulaResponse registrar(AulaRequest request) {

        log.info("Registrando aula nueva");

        validarNombreExistente(request.nombre());

        Aula aula = aulaMapper.requestAEntidad(request);

        aula.validarDatos(request.nombre(),request.capacidad());

        aulaRepository.save(aula);

        return aulaMapper.entidadAResponse(aula);

    }

    @Override
    public AulaResponse actualizar(AulaRequest request, long id) {

        log.info("Actualizando aula con id: {}",id);

        Aula aula=obtenerAulaPorId(id);

        verificarActualizarDuplicado(request.nombre(),id);

        aula.actualizar(request.nombre(),request.capacidad());

        return aulaMapper.entidadAResponse(aula);

    }

    @Override
    public void eliminar(Long id) {


        log.info("Eliminando aula con id: {}",id);

        Aula aula = obtenerAulaPorId(id);

        verificarAulaConGrupos(id);

        aulaRepository.delete(aula);

        log.info("Aula eliminada correctamente");
    }

    private Aula obtenerAulaPorId(Long id)
    {

        log.info("Obteniendo aula por id : {}",id);

        return aulaRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se encontro el aula especificada con id : "+id));

    }

    private void  validarNombreExistente(String nombre)
    {

        log.info("Verificando si hay otra aula con el nombre {}",nombre);

     if (aulaRepository.existsByNombre(nombre))
         throw new IllegalArgumentException("El nombre ya esta registrado por otra aula");

    }

    private void verificarActualizarDuplicado (String nombre,Long id)
    {

        log.info("Verificando si el nombre es unico y no corresponde a otro id: nombre[{}] id [{}]",nombre,id);

       if (aulaRepository.existsByNombreAndIdNot(nombre,id))
       throw new IllegalArgumentException("El nombre proporcionado ya se encuentra en uso por otra aula");

    }

    private void verificarAulaConGrupos(Long id)
    {

        if (grupoRepository.existsByAulaId(id))
            throw new ConflictException("El aula contiene grupos asociados");


    }







}
