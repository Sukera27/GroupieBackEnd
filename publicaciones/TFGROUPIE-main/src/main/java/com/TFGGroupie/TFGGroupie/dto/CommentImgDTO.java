package com.TFGGroupie.TFGGroupie.dto;

import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImgComment;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweetComment;
import lombok.Getter;
import lombok.Setter;
/**
 * DTO para transferir datos de un comentario de imagen.
 */
@Getter
@Setter
public class CommentImgDTO {

    private Long commentId;
    private String authorId;
    private String nombreUsuario;
    //private PublicationTweet publication;
    private Long publication;

    /**
     * Convierte una entidad de comentario de imagen a un DTO de comentario de imagen.
     *
     * @param comentarios Entidad de comentario de imagen.
     * @return DTO de comentario de imagen.
     */
    public static CommentImgDTO fromEntity(PublicationImgComment comentarios) {
        CommentImgDTO commentDTO = new CommentImgDTO();
        commentDTO.setCommentId(comentarios.getCommentId());
        commentDTO.setAuthorId(comentarios.getAuthor());
        commentDTO.setNombreUsuario(comentarios.getNombreUsuario());
        commentDTO.setPublication(comentarios.getPublicationImg().getId());

        return commentDTO;
    }
}
