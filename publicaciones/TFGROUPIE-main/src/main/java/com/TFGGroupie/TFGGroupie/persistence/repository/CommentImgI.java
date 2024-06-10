package com.TFGGroupie.TFGGroupie.persistence.repository;

import com.TFGGroupie.TFGGroupie.persistence.model.ImgLike;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImg;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImgComment;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweetComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio para gestionar los comentarios de imágenes de publicaciones.
 */
@Repository
public interface CommentImgI extends JpaRepository<PublicationImgComment, Long> {

    /**
     * Obtiene una lista de comentarios de una publicacion por su id.
     *
     * @param publicationId Identificador único de la publicacion.
     * @return Lista de comentarios de una publicacion.
     */
    List<PublicationImgComment> findByPublicationImg(PublicationImg publicationId);

    /**
     * Guarda un nuevo comentario de una publicación de imágenes.
     *
     * @param comment Comentario a guardar.
     * @return El comentario guardado.
     */
    PublicationImgComment save(PublicationImgComment comment);

}
