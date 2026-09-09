package com.example.demo.services.cursos;

import com.example.demo.dto.curso.CursoRequest;
import com.example.demo.dto.curso.CursoResponse;
import com.example.demo.entities.Curso;
import com.example.demo.exceptions.ConflictException;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.mappers.CursoMapper;
import com.example.demo.repositories.CursoRepository;
import com.example.demo.repositories.GrupoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class CursoServiceImp implements CursoService{

    private final CursoRepository cursoRepository;

    private final GrupoRepository grupoRepository;

    private  final CursoMapper cursoMapper;

    @Override
    public void eliminar(Long id) {

        log.info("Eliminando curso con id: {}",id);

        Curso curso = obtenerCursoPorId(id);

        verificarCursoConGrupos(id);

        cursoRepository.delete(curso);

        log.info("Curso eliminado correctamente");

    }

    @Override
    public CursoResponse actualizar(CursoRequest request, long id) {
        log.info("Actualizando curso con id: {}",id);

        Curso curso=obtenerCursoPorId(id);

        String nombre = request.nombre().trim();
        String descripcion = request.descripcion() == null ? null : request.descripcion().trim();

        verificarActualizarDuplicado(nombre,id);

        curso.actualizar(nombre,descripcion,request.creditos());

        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public CursoResponse registrar(CursoRequest request) {
        log.info("Registrando curso nuevo");

        String nombre = request.nombre().trim();
        validarNombreExistente(nombre);

        Curso curso=cursoMapper.requestAEntidad(request);

        String descripcion = request.descripcion() == null ? null : request.descripcion().trim();
        curso.validarDatos(nombre,descripcion,request.creditos());

        cursoRepository.save(curso);

        return cursoMapper.entidadAResponse(curso);
    }

    @Transactional(readOnly = true)
    @Override
    public CursoResponse obtenerPorId(Long id) {
        log.info("Obteniendo curso por id: {}",id);

        return cursoMapper.entidadAResponse(obtenerCursoPorId(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CursoResponse> listar() {
        log.info("Listando total de cursos");

        return cursoRepository.findAll().stream().map(cursoMapper::entidadAResponse).toList();
    }

    private Curso obtenerCursoPorId(Long id)
    {

        log.info("Obteniendo curso por id : {}",id);

        return cursoRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se encontro el curso especificado  con id : "+id));

    }

    private void  validarNombreExistente(String nombre)
    {

        log.info("Verificando si hay otro curso con el nombre {}",nombre);

        if (cursoRepository.existsByNombre(nombre.trim()))
            throw new IllegalArgumentException("El nombre ya esta registrado por otro curso");

    }

    private void verificarActualizarDuplicado (String nombre,Long id)
    {

        log.info("Verificando si el nombre es unico y no corresponde a otro id: nombre[{}] id [{}]",nombre,id);

        if (cursoRepository.existsByNombreAndIdNot(nombre.trim(),id))
            throw new IllegalArgumentException("El nombre proporcionado ya se encuentra en uso por otro curso");

    }

    private void verificarCursoConGrupos(Long id)
    {

        if (grupoRepository.existsByCursoId(id))
            throw new ConflictException("El curso contiene grupos asociados");


    }

}
