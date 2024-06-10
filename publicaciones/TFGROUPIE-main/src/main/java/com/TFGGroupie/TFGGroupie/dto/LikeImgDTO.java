package com.TFGGroupie.TFGGroupie.dto;

import com.TFGGroupie.TFGGroupie.persistence.model.ImgLike;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImg;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * DTO para transferir datos de un "like" en una publicación de imagen.
 */
@Getter
@Setter
public class LikeImgDTO {


    private Long likeId;


    private String author;


    private Long publicationImg;

    /**
     * Convierte una entidad de "like" en una DTO de "like" de imagen.
     *
     * @param publication Entidad de "like" de imagen.
     * @return DTO de "like" de imagen.
     */
    public static LikeImgDTO fromEntity(ImgLike publication) {
        LikeImgDTO publicationDTO = new LikeImgDTO();
        publicationDTO.setPublicationImg(publication.getPublicationImg().getId());
        publicationDTO.setAuthor(publication.getAuthor());
        publicationDTO.setLikeId(publication.getLikeId());

        return publicationDTO;
    }
}
