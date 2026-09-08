package com.example.demo.controllers;


import com.example.demo.dto.alumno.AlumnoRequest;
import com.example.demo.dto.alumno.AlumnoResponse;
import com.example.demo.services.alumnos.AlumnoService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "API Alumnos",description = "Metodos para la gestion de alumnos")
public class AlumnoController extends  CrudController<AlumnoRequest, AlumnoResponse, AlumnoService>{

    public AlumnoController(AlumnoService service)
    {
        super(service);
    }

}
