package com.example.demo.repositories;

import com.example.demo.entities.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroRepository extends JpaRepository <Maestro,Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByTelefono (String telefono);

    boolean existsByEmailIgnoreCaseAndIdNot(String email,Long id);

    boolean existsByTelefonoAndIdNot (String telefono,Long id );
}
