package com.example.demo.services.Grupos;

import com.example.demo.dto.grupo.GrupoRequest;
import com.example.demo.dto.grupo.GrupoResponse;
import com.example.demo.entities.Aula;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Grupo;
import com.example.demo.entities.Maestro;
import com.example.demo.exceptions.ConflictException;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.mappers.GrupoMapper;
import com.example.demo.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class GrupoServiceImp implements  GrupoService{

    private final GrupoRepository grupoRepository;

    private final GrupoMapper grupoMapper;

    private final InscripcionRepository inscripcionRepository;

    private final HorarioRepository horarioRepository;

    private final CursoRepository cursoRepository;

    private final MaestroRepository maestroRepository;

    private final AulaRepository aulaRepository;


    @Override
    @Transactional(readOnly = true)
    public List<GrupoResponse> listar() {
        log.info("Listando todos los grupos");
        return grupoRepository.findAll().stream().map(grupoMapper::entidadAResponse).toList();

    }
    @Transactional(readOnly = true)
    @Override
    public GrupoResponse obtenerPorId(Long id) {

        log.info("Busanco el grupo con id {}",id);

        return grupoMapper.entidadAResponse(buscarGrupoId(id));


    }

    @Override
    public GrupoResponse registrar(GrupoRequest request) {

        log.info("Registrando nuevo grupo verificando unicidad");

        verificarUnicidad(request.idCurso(),request.idMaestro(),request.idAula(),request.periodo());

        log.info("Verificacion correcta registrando grupo");

        Curso curso = cursoRepository.findById(request.idCurso())
               .orElseThrow(() -> new RecursoNoEncontradoException(
                "No se encontró el curso con id: " + request.idCurso()
        ));

        Maestro maestro = maestroRepository.findById(request.idMaestro())
               .orElseThrow(() -> new RecursoNoEncontradoException(
                "No se encontró el maestro con id: " + request.idMaestro()
        ));

        Aula aula = aulaRepository.findById(request.idAula())
               .orElseThrow(() -> new RecursoNoEncontradoException(
                "No se encontró el aula con id: " + request.idAula()
        ));

        Grupo grupo = grupoMapper.requestAEntidad(request,curso,maestro,aula);

        grupoRepository.save(grupo);

        return grupoMapper.entidadAResponse(grupo);

    }

    @Override
    public GrupoResponse actualizar(GrupoRequest request, long id) {

        log.info("Actulizando grupo con id: {}",id);

        Grupo grupo=buscarGrupoId(id);

        Curso curso = cursoRepository.findById(request.idCurso())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el curso con id: " + request.idCurso()
                ));

        Maestro maestro = maestroRepository.findById(request.idMaestro())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el maestro con id: " + request.idMaestro()
                ));

        Aula aula = aulaRepository.findById(request.idAula())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el aula con id: " + request.idAula()
                ));
        log.info("Verificando que no existan grupos con el mismo curso,maestro,aula y periodo");

        verificarUnicidadActualizacion(request.idCurso(),request.idMaestro(),request.idAula(), request.periodo(),id);

        grupo.actualizar(curso,maestro,aula, request.periodo());

        log.info("Grupo actualizado correctamente");

        return grupoMapper.entidadAResponse(grupo);


    }

    @Override
    public void eliminar(Long id) {

        log.info("Eliminando Grupo con id: {} verificando que no tenga registros con horarios ni inscripciones",id);

        Grupo grupo= buscarGrupoId(id);

        if (inscripcionRepository.existsByGrupoId(id)) throw  new ConflictException("No se puede eliminar el grupo , tiene registros con Inscripciones");

        if (horarioRepository.existsByGrupoId(id)) throw  new ConflictException("No se puede eliminar el grupo , tiene registros con Horarios");

        log.info("Eliminando grupo con id : {}",id);

        grupoRepository.delete(grupo);
    }

    private Grupo buscarGrupoId(Long id)
    {

        return grupoRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se ha encontrado el grupo con id: "+id));

    }

    private void verificarUnicidad(Long idCurso,Long idMaestro,Long idAula,String periodo)
    {

        if (grupoRepository.existsByCursoIdAndMaestroIdAndAulaIdAndPeriodo(idCurso,idMaestro,idAula,periodo))throw  new ConflictException(
                "No se puede hacer la insercion el id del curso , id del maestro , id del aula y periodo deben de ser unicos"
        );

    }

    private void verificarUnicidadActualizacion(Long idCurso,Long idMaestro,Long idAula,String periodo,Long id )
    {

        if (grupoRepository.existsByCursoIdAndMaestroIdAndAulaIdAndPeriodoAndIdNot(idCurso,idMaestro,idAula,periodo,id))throw  new ConflictException(
                "Ya existe otro grupo con el mismo curso ,maestro , aula y periodo"
        );

    }









}
