package com.example.demo.controllers;

import com.example.demo.dto.grupo.GrupoRequest;
import com.example.demo.dto.grupo.GrupoResponse;
import com.example.demo.services.Grupos.GrupoService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/grupos")
@Schema(description = "Metodos para la gestion de grupos")
public class GrupoController extends  CrudController<GrupoRequest, GrupoResponse, GrupoService>{

    public GrupoController(GrupoService service)
    {
        super(service);
    }


}
