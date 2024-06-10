package com.TFGGroupie.TFGGroupie.persistence.repository;

import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImg;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Repositorio para la entidad PublicationImg.
 * Proporciona métodos para acceder y manipular publicaciones de imágenes.
 */
public interface PublicationImgI extends JpaRepository<PublicationImg, Long> {
    /**
     * Obtiene una lista de publicaciones de imágenes por el ID del autor.
     *
     * @param userId ID único del autor.
     * @return Lista de publicaciones de imágenes realizadas por el autor.
     */
    List<PublicationImg> findByAuthor(String userId);
}
