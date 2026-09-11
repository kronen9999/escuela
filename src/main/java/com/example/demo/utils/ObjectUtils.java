package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectUtils {

    public static void validarNulls(Object object,String messageException)
    {

        log.info("validando que el objeto no sea null...");

        if (object == null)
            throw  new IllegalArgumentException(messageException);

    }
}
