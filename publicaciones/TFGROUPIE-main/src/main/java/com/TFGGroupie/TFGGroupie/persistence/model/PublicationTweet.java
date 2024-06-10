package com.TFGGroupie.TFGGroupie.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RS_PUBLICATIONTWEET")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationTweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RS_PUBLICATION_ID")
    private Long publicationId;

    /**
     * Autor de la publicación.
     */
    @Column(name = "RS_PUBLICATION_AUTHOR")
    private String author;

    /**
     * Texto de la publicación.
     */
    @Column(name = "RS_PUBLICATION_TEXT")
    private String text;

    /**
     * Texto de la publicación.
     */
    @Column(name = "RS_PUBLICATION_USUARIO")
    private String nombreUsuario;

    @Column(name = "publico")
    private String publico;

    /**
     * Fecha de creación de la publicación.
     */
    @Column(name = "RS_PUBLICATION_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    /**
     * Fecha de edición de la publicación.
     */
    @Column(name = "RS_PUBLICATION_EDITION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime editionDate;

    /**
     * Lista de comentarios asociados a la publicación.
     */
    @OneToMany(mappedBy = "publicationID", cascade = CascadeType.ALL)
    private List<PublicationTweetComment> comments = new ArrayList<>();

    /**
     * Lista de comentarios asociados a la publicación.
     */
    @OneToMany(mappedBy = "publicationID", cascade = CascadeType.ALL)
    private List<TweetLikes> likes = new ArrayList<>();

}
