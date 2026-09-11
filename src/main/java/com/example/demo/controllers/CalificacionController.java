package com.example.demo.controllers;

import com.example.demo.dto.calificacion.CalificacionRequest;
import com.example.demo.dto.calificacion.CalificacionResponse;
import com.example.demo.services.calificaciones.CalificacionService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/calificaciones")
@Schema(description = "Metodos para la gestion de calificaciones")
public class CalificacionController extends CrudController<CalificacionRequest, CalificacionResponse, CalificacionService> {

    public CalificacionController(CalificacionService service)
    {
        super(service);
    }

}
