package com.example.demo.controllers;

import com.example.demo.dto.Inscripcion.InscripcionRequest;
import com.example.demo.dto.Inscripcion.InscripcionResponse;
import com.example.demo.services.Inscripciones.InscripcionesService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/inscripciones")
@Schema(description = "Metodos para la gestion de inscripciones")
public class InscripcionController extends CrudController<InscripcionRequest, InscripcionResponse, InscripcionesService>{
    public InscripcionController (InscripcionesService service)
    {
        super(service);

    }

}
