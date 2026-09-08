package com.example.demo.repositories;

import com.example.demo.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
    boolean existsByAlumnoId(Long  idAlumno);
}
