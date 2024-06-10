package com.TFGGroupie.TFGGroupie.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
/**
 * Implementación del servicio JWTserviceI para la extracción de datos de un JWT.
 */
@Service
@Slf4j
public class JWTserviceImpl implements JWTserviceI {
    /**
     * Obtiene el ID y otros datos de un JWT.
     *
     * @param jwt Token JWT del cual se extraerá el ID.
     * @return Un mapa con el ID y otros datos extraídos del JWT.
     * @throws RuntimeException Si ocurre un error al parsear el JWT o si el token no tiene el formato esperado.
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
