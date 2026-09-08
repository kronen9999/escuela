package com.example.demo.services.alumnos;

import com.example.demo.dto.alumno.AlumnoRequest;
import com.example.demo.dto.alumno.AlumnoResponse;
import com.example.demo.repositories.AlumnoRepository;
import com.example.demo.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlumnoService extends CrudService<AlumnoRequest, AlumnoResponse> {




}
