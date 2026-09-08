package com.example.demo.mappers;

public interface CommonMapper <RQ,RS,E>{

    E requestAEntiddad (RQ request);

    RS entidadAResponse (E entidad);
}
