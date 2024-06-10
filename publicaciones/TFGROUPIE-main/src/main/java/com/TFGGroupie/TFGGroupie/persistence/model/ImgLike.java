package com.TFGGroupie.TFGGroupie.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "RS_LIKES_IMG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImgLike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RS_LIKE_ID")
    private Long likeId;

    /**
     * Autor del like.
     */
    @Column(name = "RS_LIKE_AUTHOR")
    private String author;

    /**
     * Publicación a la que pertenece el like.
     */
    @ManyToOne
    @JoinColumn(name = "RS_PUBLICATION_ID", nullable = false)
    private PublicationImg publicationImg;
}
