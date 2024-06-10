package com.TFGGroupie.TFGGroupie.persistence.repository;

import com.TFGGroupie.TFGGroupie.persistence.model.ImgLike;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImg;
import com.TFGGroupie.TFGGroupie.persistence.model.TweetLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 * Repositorio para la entidad ImgLike.
 * Proporciona métodos para acceder y manipular los likes de imágenes.
 */
public interface LikeImgI extends JpaRepository<ImgLike, Long> {
    /**
     * Encuentra un like de imagen específico por su autor y la imagen publicada.
     *
     * @param jwt          Token de autorización del autor del like.
     * @param idPublication La publicación de imagen por la cual se hizo el like.
     * @return El objeto ImgLike que corresponde al like encontrado, o null si no se encuentra.
     */
    ImgLike findByAuthorAndPublicationImg(String jwt, PublicationImg idPublication);
    /**
     * Obtiene el número de likes de una publicación de imagen específica.
     *
     * @param id Identificador único de la publicación de imagen.
     * @return El número de likes de la publicación de imagen.
     */
    @Query(value = "select count(rs_publication_id) FROM publicationDB.rs_likes_img WHERE rs_publication_id= :id", nativeQuery = true)
    int findLikesByPublication(@Param("id") Long id);

}
