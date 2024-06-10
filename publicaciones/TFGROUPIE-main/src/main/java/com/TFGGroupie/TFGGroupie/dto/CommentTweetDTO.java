package com.TFGGroupie.TFGGroupie.dto;

import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweetComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
/**
 * DTO para transferir datos de un comentario de tweet.
 */
@Getter
@Setter
public class CommentTweetDTO {

    private Long commentId;
    private String author;
    private String nombreUsuario;
    //private PublicationTweet publication;
    private Long publicationID;
    /**
     * Convierte una entidad de comentario de tweet a un DTO de comentario de tweet.
     *
     * @param comentarios Entidad de comentario de tweet.
     * @return DTO de comentario de tweet.
     */

    public static CommentTweetDTO fromEntity(PublicationTweetComment comentarios) {
        CommentTweetDTO commentDTO = new CommentTweetDTO();
        commentDTO.setCommentId(comentarios.getCommentId());
        commentDTO.setAuthor(comentarios.getAuthor());
        commentDTO.setNombreUsuario(comentarios.getNombreUsuario());
        commentDTO.setPublicationID(comentarios.getPublicationID().getPublicationId());

        return commentDTO;
    }

}
