package com.example.demo.mappers;

import com.example.demo.dto.aula.AulaRequest;
import com.example.demo.dto.aula.AulaResponse;
import com.example.demo.entities.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper implements CommonMapper <AulaRequest, AulaResponse, Aula>{

    @Override
    public Aula requestAEntidad(AulaRequest request) {

        if (request== null) return null;

        return Aula.builder()
                .nombre(request.nombre())
                .capacidad(request.capacidad())
                .build();

    }

    @Override
    public AulaResponse entidadAResponse(Aula entidad) {
        if  (entidad ==null) return null;

        return new AulaResponse(
          entidad.getId(),
          entidad.getNombre(),
          entidad.getCapacidad()
        );
    }
}
