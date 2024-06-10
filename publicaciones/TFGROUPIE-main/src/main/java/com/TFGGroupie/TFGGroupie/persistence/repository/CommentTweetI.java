package com.TFGGroupie.TFGGroupie.persistence.repository;

import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweetComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio para la entidad PublicationTweetComment.
 * Proporciona métodos para acceder y manipular comentarios de publicaciones de tweets.
 */
@Repository
public interface CommentTweetI extends JpaRepository<PublicationTweetComment, Long> {

    /**
     * Obtiene una lista de comentarios de una publicacion por su id.
     *
     * @param publicationId Identificador único de la publicacion.
     * @return Lista de comentarios de una publicacion.
     */
    List<PublicationTweetComment> findByPublicationID(PublicationTweet publicationId);
    /**
     * Guarda un nuevo comentario de publicación de tweet.
     *
     * @param comment El comentario de la publicación de tweet a guardar.
     * @return El comentario de la publicación de tweet guardado.
     */
    PublicationTweetComment save(PublicationTweetComment comment);
}
