package com.TFGGroupie.TFGGroupie.dto;

import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImg;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
/**
 * DTO para transferir datos de una publicación de imagen.
 */
@Getter
@Setter
public class PublicationImgDTO {


    private Long id;
    private String authorId;
    private String username;
    private byte[] data;
    private LocalDateTime creationDate;
    private LocalDateTime editionDate;
    public  String publico;

    /**
     * Convierte una entidad de PublicacionImg a un DTO de PublicationImgDTO.
     *
     * @param publication Entidad de la publicación de imagen.
     * @return DTO de la publicación de imagen.
     */
    public static PublicationImgDTO fromEntity(PublicationImg publication) {
        PublicationImgDTO publicationDTO = new PublicationImgDTO();
        publicationDTO.setId(publication.getId());
        publicationDTO.setAuthorId(publication.getAuthor());
        publicationDTO.setUsername(publication.getName());
        publicationDTO.setData(publication.getData());
        publicationDTO.setEditionDate(publication.getEditionDate());
        publicationDTO.setCreationDate(publication.getCreationDate());
        publicationDTO.setPublico(publication.getPublico());
        return publicationDTO;
    }
}
