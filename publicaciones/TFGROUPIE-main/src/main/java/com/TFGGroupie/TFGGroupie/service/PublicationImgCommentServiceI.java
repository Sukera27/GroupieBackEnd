package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.CommentImgDTO;
import com.TFGGroupie.TFGGroupie.dto.CommentTweetDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImgComment;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweetComment;

import java.util.List;
/**
 * Interfaz que define los métodos para gestionar comentarios en publicaciones de imágenes.
 */
public interface PublicationImgCommentServiceI {
    /**
     * Obtiene una lista de comentarios de una publicación de imagen por su ID.
     *
     * @param id ID de la publicación de imagen.
     * @return Lista de comentarios de la publicación de imagen.
     */
    List<CommentImgDTO> getCommentsByPublicationId(Long id);
    /**
     * Publica un nuevo comentario en una publicación de imagen.
     *
     * @param token Token JWT del usuario que realiza el comentario.
     * @param texto Texto del comentario.
     * @param id    ID de la publicación de imagen.
     * @return El comentario publicado.
     */
    PublicationImgComment publicarComentario (String token, String texto, Long id);
}
