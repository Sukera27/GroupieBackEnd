package com.MicroServicioMensajeria.MicroServicioMensajeria.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
/**
 * Implementación del servicio JWTI para manejar JSON Web Tokens (JWT).
 */
@Service
@Slf4j
public class JWTImpl implements JWTI{
    /**
     * Extrae información de un token JWT.
     *
     * @param jwt el token JWT del cual se extraerán los datos
     * @return un mapa que contiene el userId y el username extraídos del token JWT
     * @throws RuntimeException si el token no está presente, tiene un formato incorrecto o no se puede parsear
     */
    @Override
    public Map<String, String> getId(String jwt) {
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);

            Base64.Decoder decoder = Base64.getDecoder();
            String[] array = jwt.split("\\.");

            if (array.length >= 2) {
                String decodedJson = new String(decoder.decode(array[1]), StandardCharsets.UTF_8);
                try {
                    JsonNode jsonNode = new ObjectMapper().readTree(decodedJson);
                    String userId = jsonNode.get("userId").asText();
                    String username = jsonNode.get("username").asText();
                    Map<String, String> userIdAndUsername = new HashMap<>();
                    userIdAndUsername.put("userId", userId);
                    userIdAndUsername.put("username", username);
                    return userIdAndUsername;
                } catch (Exception e) {
                    throw new RuntimeException("Error al parsear el JWT", e);
                }
            } else {
                throw new RuntimeException("Token JWT no tiene el formato esperado");
            }
        } else {
            throw new RuntimeException("No se encontró el token");
        }
    }
}
