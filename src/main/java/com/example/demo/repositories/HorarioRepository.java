package com.example.demo.repositories;

import com.example.demo.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HorarioRepository extends JpaRepository<Horario,Long> {

    boolean  existsByGrupoId (Long id);

}
