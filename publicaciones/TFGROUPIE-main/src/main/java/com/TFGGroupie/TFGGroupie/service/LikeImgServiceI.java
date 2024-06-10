package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.LikeImgDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.ImgLike;
import com.TFGGroupie.TFGGroupie.persistence.model.TweetLikes;
/**
 * Interfaz que define los métodos para gestionar likes en publicaciones de imágenes.
 */
public interface LikeImgServiceI {
    /**
     * Da like o quita el like de una publicación de imagen.
     *
     * @param jwt          Token JWT del usuario que da o quita el like.
     * @param idPublication ID de la publicación de imagen.
     * @return true si se dio like, false si se quitó el like.
     */
    Boolean likePublication(String jwt,Long idPublication);
    /**
     * Verifica si un usuario ha dado like a una publicación de imagen.
     *
     * @param jwt          Token JWT del usuario.
     * @param idPublication ID de la publicación de imagen.
     * @return DTO que representa la información del like.
     */
    LikeImgDTO existeLike(String jwt, Long idPublication);
    /**
     * Obtiene la cantidad de likes de una publicación de imagen.
     *
     * @param id ID de la publicación de imagen.
     * @return Número de likes de la publicación.
     */
    int findLikesByPublication(Long id);


}
