package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.CommentTweetDTO;
import com.TFGGroupie.TFGGroupie.dto.PublicationTweetDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweetComment;

import java.util.List;
/**
 * Interfaz que define los métodos para gestionar los comentarios de publicaciones de tweets.
 */
public interface PublicationTweetCommentServiceI {
    /**
     * Obtiene todos los comentarios asociados a una publicación de tweet específica.
     *
     * @param id Identificador de la publicación de tweet.
     * @return Lista de comentarios de la publicación de tweet.
     */
    List<CommentTweetDTO> getCommentsByPublicationId(Long id);
    /**
     * Permite a un usuario publicar un nuevo comentario en una publicación de tweet.
     *
     * @param token Token JWT del usuario.
     * @param texto Contenido del comentario.
     * @param id    Identificador de la publicación de tweet.
     * @return DTO del comentario publicado.
     */
    CommentTweetDTO publicarComentario (String token, String texto, Long id);


}
