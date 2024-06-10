package com.TFGGroupie.TFGGroupie.dto;

import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
/**
 * DTO para transferir datos de una publicación de tweet.
 */
@Getter
@Setter
public class PublicationTweetDTO {

    private Long publicationId;
    private String authorId;
    private String text;
    private LocalDateTime creationDate;
    private String username;
    private LocalDateTime editionDate;
    public  String publico;

    /**
     * Convierte una entidad Publication a un objeto PublicationDTO.
     *
     * @param publication Entidad Publication a convertir.
     * @return Objeto PublicationDTO creado a partir de la entidad Publication.
     */
    public static PublicationTweetDTO fromEntity(PublicationTweet publication) {
        PublicationTweetDTO publicationDTO = new PublicationTweetDTO();
        publicationDTO.setPublicationId(publication.getPublicationId());
        publicationDTO.setAuthorId(publication.getAuthor());
        publicationDTO.setText(publication.getText());
        publicationDTO.setUsername(publication.getNombreUsuario());
        publicationDTO.setCreationDate(publication.getCreationDate());
        publicationDTO.setEditionDate(publication.getEditionDate());
        publicationDTO.setPublico(publication.getPublico());
        return publicationDTO;
    }
}
