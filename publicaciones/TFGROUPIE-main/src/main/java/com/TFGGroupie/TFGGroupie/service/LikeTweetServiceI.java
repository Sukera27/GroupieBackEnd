package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.CommentTweetDTO;
import com.TFGGroupie.TFGGroupie.dto.LikeDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.TweetLikes;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Interfaz que define los métodos para gestionar los likes en las publicaciones de tweet.
 */
public interface LikeTweetServiceI {
    /**
     * Da like o quita el like de una publicación de tweet.
     *
     * @param jwt          Token JWT del usuario que da o quita el like.
     * @param idPublication ID de la publicación de tweet.
     * @return true si se dio like, false si se quitó el like.
     */
    Boolean likePublication(String jwt,Long idPublication);
    /**
     * Verifica si un usuario ha dado like a una publicación de tweet.
     *
     * @param jwt          Token JWT del usuario.
     * @param idPublication ID de la publicación de tweet.
     * @return DTO que representa la información del like.
     */
    LikeDTO existeLike(String jwt, Long idPublication);
    /**
     * Obtiene la cantidad de likes de una publicación de tweet.
     *
     * @param id ID de la publicación de tweet.
     * @return Número de likes de la publicación.
     */
    int findLikesByPublication(Long id);

}
