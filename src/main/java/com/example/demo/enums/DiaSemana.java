package com.example.demo.enums;


import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DiaSemana {
    Lunes ("Lunes"),
    Martes("Martes"),
    Miercoles ("Miercoles"),
    Jueves("Jueves"),
    Viernes("Viernes"),
    Sabado("Sabado");

    private  final String descripcion;

    public static DiaSemana obtenerDiaSemanaPorDescripcion(String descripcion)
    {
        StringCustomUtils.validarNoVacio(descripcion,"La descripcion es requerida");

        String descripcionNormalizada =StringCustomUtils.quitarAcentos(descripcion);




        for (DiaSemana diaSemana:values())
        {
            if (StringCustomUtils.quitarAcentos(diaSemana.descripcion).equalsIgnoreCase(descripcionNormalizada))

            {
                return  diaSemana;
            }
        }

        throw  new RecursoNoEncontradoException("No existe un dia de la semana con la descripcion  : "+descripcion );


    }
}
