package com.TFGGroupie.TFGGroupie.dto;

import com.TFGGroupie.TFGGroupie.persistence.model.ImgLike;
import com.TFGGroupie.TFGGroupie.persistence.model.TweetLikes;
import lombok.Getter;
import lombok.Setter;
/**
 * DTO para transferir datos de un "like" en una publicación de tweet.
 */
@Getter
@Setter
public class LikeDTO {

    private Long likeId;


    private String author;


    private Long publicationID;


    /**
     * Convierte una entidad de "like" en una DTO de "like".
     *
     * @param publication Entidad de "like".
     * @return DTO de "like".
     */
    public static LikeDTO fromEntity(TweetLikes publication) {
        LikeDTO publicationDTO = new LikeDTO();
        publicationDTO.setPublicationID(publication.getPublicationID().getPublicationId());
        publicationDTO.setAuthor(publication.getAuthor());
        publicationDTO.setLikeId(publication.getLikeId());

        return publicationDTO;
    }
}
