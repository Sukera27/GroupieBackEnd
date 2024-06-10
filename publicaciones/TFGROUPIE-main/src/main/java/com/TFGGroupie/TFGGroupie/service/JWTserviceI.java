package com.TFGGroupie.TFGGroupie.service;

import java.util.Map;
/**
 * Interfaz para el servicio JWT.
 * Define métodos para obtener el ID y otros datos de un JWT.
 */
public interface JWTserviceI {

    Map<String, String> getId(String jwt);

}
