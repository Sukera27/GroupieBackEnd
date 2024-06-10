package com.repaso.repaso.security.auth.service;



import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repaso.repaso.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class JWTService implements JWTServiceI{

    String SECRET_KEY = "lMCvj7Sirkk41OpuXDBKoSA1YeQ4aTeHmP4gzoyoaLk=";
    private final long TOKEN_DURATION = 7 * 24 * 60 * 60 * 1000; // Una semana en milisegundos

    @Override
    public String getToken(User user) {
        return getToken(getUserClaims(user), user);
    }
    /**
     * Genera un token JWT para el usuario especificado.
     *
     * @param user Usuario para el cual se genera el token.
     * @return Token JWT generado.
     */
    @Override
    public String getToken(Map<String, Object> extraClaims, User user) {
        Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_DURATION);

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(getKey())
                .compact();
    }

    /**
     * Obtiene la clave secreta utilizada para firmar y verificar el token JWT.
     *
     * @return Clave secreta.
     */
    @Override
    public SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * Obtiene los reclamos del usuario que se incluirán en el token JWT.
     *
     * @param user Usuario para el cual se obtienen los reclamos.
     * @return Mapa de reclamos del usuario.
     */
    private Map<String, Object> getUserClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());
        claims.put("favoriteColor", user.getFavoriteColor());
        // Añade más campos según lo necesario
        return claims;
    }

    /**
     * Obtiene el nombre de usuario desde el token JWT.
     *
     * @param token Token JWT del cual se obtendrá el nombre de usuario.
     * @return Nombre de usuario.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }
    /**
     * Verifica si un token JWT es válido para el usuario especificado.
     *
     * @param token       Token JWT a verificar.
     * @param userDetails Detalles del usuario a comparar con el token.
     * @return true si el token es válido, false de lo contrario.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    /**
     * Obtiene todos los reclamos (claims) del token JWT.
     *
     * @param token Token JWT del cual se obtendrán los reclamos.
     * @return Reclamos del token JWT.
     */
    private Claims getAllClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    /**
     * Obtiene un reclamo específico del token JWT.
     *
     * @param token          Token JWT del cual se obtendrá el reclamo.
     * @param claimsResolver Resolvedor de reclamos para obtener el reclamo específico.
     * @param <T>            Tipo de reclamo.
     * @return Reclamo específico del token JWT.
     */
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    /**
     * Obtiene la fecha de expiración del token JWT.
     *
     * @param token Token JWT del cual se obtendrá la fecha de expiración.
     * @return Fecha de expiración del token JWT.
     */
    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }
    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token Token JWT a verificar.
     * @return true si el token ha expirado, false de lo contrario.
     */
    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }

    /**
     * Obtiene el identificador de usuario y el nombre de usuario desde el token JWT.
     *
     * @param jwt Token JWT del cual se obtendrá el identificador de usuario y el nombre de usuario.
     * @return Mapa con el identificador de usuario y el nombre de usuario.
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
