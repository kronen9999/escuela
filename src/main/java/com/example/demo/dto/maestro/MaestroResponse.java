package com.example.demo.dto.maestro;

public record MaestroResponse(

        Long id,
        String nombre,
        String apellidoPaterno,
        String apeliidoMaterno,
        String email,
        String telefono

) {
}
