package com.example.demo.controllers;

import com.example.demo.dto.curso.CursoRequest;
import com.example.demo.dto.curso.CursoResponse;
import com.example.demo.entities.Curso;
import com.example.demo.services.cursos.CursoService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/cursos")
@Schema(description = "Metodos para la gestion de cursos")
public class CursoController extends  CrudController<CursoRequest, CursoResponse, CursoService>{

    public CursoController(CursoService service)
    {
        super(service);
    }

}
