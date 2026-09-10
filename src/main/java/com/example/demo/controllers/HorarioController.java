package com.example.demo.controllers;

import com.example.demo.dto.horario.HorarioRequest;
import com.example.demo.dto.horario.HorarioResponse;
import com.example.demo.services.Horarios.HorarioService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/horarios")
@Schema(description = "Metodos para la gestion de horarios")
public class HorarioController extends CrudController<HorarioRequest, HorarioResponse, HorarioService>{


    public HorarioController(HorarioService service)
    {

        super(service);

    }
}
