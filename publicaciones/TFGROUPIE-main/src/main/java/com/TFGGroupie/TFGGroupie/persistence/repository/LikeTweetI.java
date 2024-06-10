package com.TFGGroupie.TFGGroupie.persistence.repository;


import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import com.TFGGroupie.TFGGroupie.persistence.model.TweetLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Repositorio para la entidad TweetLikes.
 * Proporciona métodos para acceder y manipular los likes de tweets.
 */
public interface LikeTweetI extends JpaRepository<TweetLikes, Long> {
    /**
     * Encuentra un like de tweet específico por su autor y el tweet publicado.
     *
     * @param jwt          Token de autorización del autor del like.
     * @param idPublication El tweet por el cual se hizo el like.
     * @return El objeto TweetLikes que corresponde al like encontrado, o null si no se encuentra.
     */
    TweetLikes findByAuthorAndPublicationID(String jwt, PublicationTweet idPublication);
    /**
     * Obtiene el número de likes de un tweet específico.
     *
     * @param id Identificador único del tweet.
     * @return El número de likes del tweet.
     */
    @Query(value = "select count(rs_publication_id) FROM publicationDB.rs_likes WHERE rs_publication_id= :id", nativeQuery = true)
    int findLikesByPublication(@Param("id") Long id);

}
