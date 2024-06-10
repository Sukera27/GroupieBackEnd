package com.TFGGroupie.TFGGroupie.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "RS_PUBLICATIONTWEET_COMMENTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationTweetComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RS_PUBLICATION_COMMENT_ID")
    private Long commentId;

    /**
     * Autor de la publicación.
     */
    @Column(name = "RS_COMMENT_AUTHOR")
    private String author;

    /**
     * Texto del comentario.
     */
    @Column(name = "RS_PUBLICATION_COMMENT_TEXT")
    private String nombreUsuario;

    /**
     * Publicación a la que pertenece el comentario.
     */


    @ManyToOne
    @JoinColumn(name = "RS_PUBLICATION_ID")
    private PublicationTweet publicationID;


    /**
     * ID de la publicación a la que pertenece el comentario.
     */
    //@Column(name = "RS_PUBLICATION_ID")
    //private Long publicationID;

}
