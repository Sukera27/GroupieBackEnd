package com.repaso.repaso.security.auth.service;

import com.repaso.repaso.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Map;

public interface JWTServiceI {
    /**
     * Genera un token JWT para el usuario especificado.
     *
     * @param user Usuario para el cual se genera el token.
     * @return Token JWT generado.
     */
    String getToken(User user);
    /**
     * Genera un token JWT para el usuario especificado con reclamos adicionales.
     *
     * @param extraClaims Reclamos adicionales para incluir en el token.
     * @param user        Usuario para el cual se genera el token.
     * @return Token JWT generado.
     */
    String getToken(Map<String, Object> extraClaims, User user);
    /**
     * Obtiene la clave secreta utilizada para firmar y verificar el token JWT.
     *
     * @return Clave secreta.
     */
    Key getKey();
    /**
     * Obtiene el nombre de usuario desde el token JWT.
     *
     * @param token Token JWT del cual se obtendrá el nombre de usuario.
     * @return Nombre de usuario.
     */
    String getUsernameFromToken(String token);
    /**
     * Verifica si un token JWT es válido para el usuario especificado.
     *
     * @param token       Token JWT a verificar.
     * @param userDetails Detalles del usuario a comparar con el token.
     * @return true si el token es válido, false de lo contrario.
     */
    boolean isTokenValid(String token, UserDetails userDetails);
    /**
     * Obtiene el identificador de usuario y el nombre de usuario desde el token JWT.
     *
     * @param jwt Token JWT del cual se obtendrá el identificador de usuario y el nombre de usuario.
     * @return Mapa con el identificador de usuario y el nombre de usuario.
     */
    Map<String, String> getId(String jwt);
}
