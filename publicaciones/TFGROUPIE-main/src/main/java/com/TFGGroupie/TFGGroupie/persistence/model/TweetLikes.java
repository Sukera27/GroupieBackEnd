package com.TFGGroupie.TFGGroupie.persistence.model;

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
@Table(name = "RS_LIKES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TweetLikes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RS_LIKE_ID")
    private Long likeId;

    /**
     * Autor de la publicación.
     */
    @Column(name = "RS_LIKE_AUTHOR")
    private String author;

    /**
     * ID de la publicación a la que pertenece el comentario.
     */
    //@Column(name = "RS_PUBLICATION_ID")
    //private PublicationTweet publicationID;

    @ManyToOne
    @JoinColumn(name = "RS_PUBLICATION_ID")
    private PublicationTweet publicationID;

}
