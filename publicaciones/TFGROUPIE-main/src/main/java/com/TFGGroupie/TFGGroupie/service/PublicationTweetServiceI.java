package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.PublicationTweetDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;

import java.util.List;
/**
 * Interfaz que define los métodos para gestionar las publicaciones de tweets.
 */
public interface PublicationTweetServiceI {
    /**
     * Obtiene todas las publicaciones de tweets de un usuario a partir de su token JWT.
     *
     * @param token Token JWT del usuario.
     * @return Lista de publicaciones de tweets del usuario.
     */
    List<PublicationTweetDTO> getPublicationsByUserToken(String token);

    /**
     * Obtiene todas las publicaciones de tweets de un usuario a partir de su identificador.
     *
     * @param token Token JWT del usuario.
     * @return Lista de publicaciones de tweets del usuario.
     */
    List<PublicationTweetDTO> getPublicationsByUserID(String token);
    /**
     * Permite a un usuario publicar un nuevo tweet.
     *
     * @param token   Token JWT del usuario.
     * @param texto   Contenido del tweet.
     * @param publcico Tipo de visibilidad del tweet.
     * @return La publicación de tweet creada.
     */
    PublicationTweet publicarTweet (String token, String texto, String publcico);

}
