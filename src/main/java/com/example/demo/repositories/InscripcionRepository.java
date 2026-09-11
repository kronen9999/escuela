package com.example.demo.repositories;

import com.example.demo.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
    boolean existsByAlumnoId(Long  idAlumno);

    boolean  existsByGrupoId (Long id);

    boolean existsByAlumnoIdAndGrupoId(Long idAlumno, Long idGrupo);

    boolean existsByAlumnoIdAndGrupoIdAndIdNot(Long idAlumno, Long idGrupo, Long id);
}
