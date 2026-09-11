package com.example.demo.controllers;

import com.example.demo.dto.aula.AulaRequest;
import com.example.demo.dto.aula.AulaResponse;
import com.example.demo.services.aulas.AulaService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aulas")
@Schema(description = "Metodos para la gestion de aulas")
public class AulaController extends CrudController<AulaRequest, AulaResponse, AulaService>{

    public AulaController(AulaService service)
    {
        super(service);
    }


}
