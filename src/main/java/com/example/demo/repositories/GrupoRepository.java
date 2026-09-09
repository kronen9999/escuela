package com.example.demo.repositories;

import com.example.demo.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo,Long> {

    boolean existsByMaestroId(Long  idMaestro);

    boolean existsByAulaId(Long AulaId);
}
