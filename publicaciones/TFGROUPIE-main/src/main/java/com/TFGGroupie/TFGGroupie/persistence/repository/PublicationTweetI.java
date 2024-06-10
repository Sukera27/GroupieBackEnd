package com.TFGGroupie.TFGGroupie.persistence.repository;

import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio para la entidad PublicationTweet.
 * Proporciona métodos para acceder y manipular publicaciones de tweets.
 */
@Repository
public interface PublicationTweetI extends JpaRepository<PublicationTweet, Long> {

    /**
     * Obtiene una lista de publicaciones realizadas por un usuario específico.
     *
     * @param userId Identificador único del usuario.
     * @return Lista de publicaciones realizadas por el usuario especificado.
     */
    List<PublicationTweet> findByAuthor(String userId);
    /**
     * Guarda una nueva publicación de tweet.
     *
     * @param publicationTweet La publicación de tweet a guardar.
     * @return La publicación de tweet guardada.
     */
    PublicationTweet save(PublicationTweet publicationTweet);


}
