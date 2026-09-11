package com.example.demo.repositories;

import com.example.demo.entities.Horario;
import com.example.demo.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface HorarioRepository extends JpaRepository<Horario,Long> {

    boolean  existsByGrupoId (Long id);

    @Query("""
            SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END
            FROM Horario h
            WHERE h.grupo.id = :idGrupo
              AND h.diaSemana = :dia
              AND h.horaInicio < :horaFin
              AND h.horaFIn > :horaInicio
            """)
    boolean existeTraslapeEnGrupo(
            @Param("idGrupo") Long idGrupo,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );

    @Query("""
            SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END
            FROM Horario h
            WHERE h.id <> :idHorario
              AND h.grupo.id = :idGrupo
              AND h.diaSemana = :dia
              AND h.horaInicio < :horaFin
              AND h.horaFIn > :horaInicio
            """)
    boolean existeTraslapeEnGrupoExcepto(
            @Param("idHorario") Long idHorario,
            @Param("idGrupo") Long idGrupo,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );

    @Query("""
            SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END
            FROM Horario h
            WHERE h.grupo.aula.id = :idAula
              AND h.grupo.periodo = :periodo
              AND h.diaSemana = :dia
              AND h.horaInicio < :horaFin
              AND h.horaFIn > :horaInicio
            """)
    boolean existeTraslapeEnAula(
            @Param("idAula") Long idAula,
            @Param("periodo") String periodo,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );

    @Query("""
            SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END
            FROM Horario h
            WHERE h.id <> :idHorario
              AND h.grupo.aula.id = :idAula
              AND h.grupo.periodo = :periodo
              AND h.diaSemana = :dia
              AND h.horaInicio < :horaFin
              AND h.horaFIn > :horaInicio
            """)
    boolean existeTraslapeEnAulaExcepto(
            @Param("idHorario") Long idHorario,
            @Param("idAula") Long idAula,
            @Param("periodo") String periodo,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );
}
