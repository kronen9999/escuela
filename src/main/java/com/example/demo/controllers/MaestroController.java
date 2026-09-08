package com.example.demo.controllers;

import com.example.demo.dto.maestros.MaestroRequest;
import com.example.demo.dto.maestros.MaestroResponse;
import com.example.demo.services.maestros.MaestroService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maestros")
@Schema(description = "Metodos para la gestion de maestros")
public class MaestroController extends CrudController<MaestroRequest, MaestroResponse, MaestroService>{

    public MaestroController(MaestroService service)
    {
        super(service);
    }
}
