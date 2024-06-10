package com.MicroServicioMensajeria.MicroServicioMensajeria.service;

import java.util.Map;
/**
 * Interfaz para la gestión de JWT (JSON Web Tokens).
 * Proporciona métodos para extraer información de los tokens JWT.
 */
public interface JWTI {
    /**
     * Obtiene un mapa de datos a partir de un token JWT.
     *
     * @param jwt el token JWT del cual se extraerán los datos
     * @return un mapa que contiene la información extraída del token JWT
     */
    Map<String, String> getId(String jwt);
}
