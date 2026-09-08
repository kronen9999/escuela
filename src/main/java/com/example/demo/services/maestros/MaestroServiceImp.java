package com.example.demo.services.maestros;

import com.example.demo.dto.maestros.MaestroRequest;
import com.example.demo.dto.maestros.MaestroResponse;
import com.example.demo.entities.Maestro;
import com.example.demo.exceptions.EntidadRelacionadaException;
import com.example.demo.mappers.MaestroMapper;
import com.example.demo.repositories.GrupoRepository;
import com.example.demo.repositories.MaestroRepository;
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
public class MaestroServiceImp  implements  MaestroService{


    private final MaestroRepository maestroRepository;

    private final MaestroMapper maestroMapper;

    private final GrupoRepository grupoRepository;

    @Override
    public List<MaestroResponse> listar() {
        return  maestroRepository.findAll().stream().map(maestroMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MaestroResponse obtenerPorId(Long id) {
        log.info("Listando todos los maestros");

        return maestroMapper.entidadAResponse(obtenerMaestro(id));

    }

    @Override
    public MaestroResponse registrar(MaestroRequest request) {
        log.info("Registrando nuevo maestro");

        //validar datos

        validarDatosUnicos(request);

        Maestro maestro=maestroMapper.requestAEntidad(request);

        maestroRepository.save(maestro);

        log.info("Nuevo maestro {} registrado",maestro.getNombre());

        return null;
    }

    @Override
    public MaestroResponse actualizar(MaestroRequest request, long id) {
        Maestro maestro = obtenerMaestro(id);

        log.info("Actualizando maestro con id: {}",id);

        validarCambiosUnicos(request,id);

        maestro.actulizar(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.email(),
                request.telefono());

        log.info("Maestro {} actualizado correctamente",maestro.getNombre());

        return maestroMapper.entidadAResponse(maestro);
    }

    @Override
    public void eliminar(Long id) {

        Maestro maestro = obtenerMaestro(id);

        log.info("Eliminando maesrros con id :",id);

         if (grupoRepository.existsByMaestroId(id))
             throw  new EntidadRelacionadaException("No se puede eliminar el maestro tiene grupos asignados");

        maestroRepository.delete(maestro);

        log.info("Maestro {} eliminando correctamente ",maestro.getNombre());
    }

    private Maestro obtenerMaestro(Long id)
    {

        return ServiceUtils.obtenerEntidadOException(maestroRepository,id,Maestro.class);

    }

    private void validarDatosUnicos (MaestroRequest request)
    {
        log.info("Validando email unico ...");

        if (maestroRepository.existsByEmailIgnoreCase(request.email().trim()))
            throw  new IllegalArgumentException("Ya existe un maestro registrado con el email:"+request.email());

        log.info("Validando telefono unico ...");
        if (maestroRepository.existsByTelefono(request.telefono().trim()))
        throw  new IllegalArgumentException("Ya exisite un maestro registrado con este telefono"+ request.telefono());
    }

    private void validarCambiosUnicos (MaestroRequest request,Long id)
    {
        log.info("Validando cambio en  email unico ...");

        if (maestroRepository.existsByEmailIgnoreCaseAndIdNot(request.email().trim(),id))
            throw  new IllegalArgumentException("Ya existe un maestro registrado con el email:"+request.email());

        log.info("Validando cambio en  telefono unico ...");
        if (maestroRepository.existsByTelefonoAndIdNot(request.telefono().trim(),id))
        throw  new IllegalArgumentException("Ya exisite un maestro registrado con este telefono"+ request.telefono());
    }

}
