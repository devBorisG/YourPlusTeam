package com.uco.yourplus.apiyourplus.rabbit.util;

import java.util.Optional;

public interface MapperJsonObjeto {

    Optional<String> ejecutar (Object objeto);
    <T> Optional<T> ejecutar(String json, Class <T> claseDestino);
    Optional<String> ejecutarGson(Object objeto);
}
