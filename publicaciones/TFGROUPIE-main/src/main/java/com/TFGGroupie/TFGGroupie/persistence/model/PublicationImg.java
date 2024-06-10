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
@Table(name = "RS_PUBLICATIONIMG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationImg implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Nombre de la columna en la base de datos
    private Long id;

    @Column(name = "RS_PUBLICATION_AUTHOR", nullable = false)
    private String author;

    @Column(name = "RS_PUBLICATION_NAME", nullable = false)
    private String name;

    @Lob
    @Column(name = "image", columnDefinition="LONGBLOB")
    private byte[] data;

    /**
     * Fecha de creación de la publicación.
     */
    @Column(name = "RS_PUBLICATION_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    @Column(name = "publico")
    private String publico;

    /**
     * Fecha de edición de la publicación.
     */
    @Column(name = "RS_PUBLICATION_EDITION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime editionDate;


    /**
     * Lista de comentarios asociados a la publicación.
     */
    @OneToMany(mappedBy = "publicationImg", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PublicationImgComment> comments = new ArrayList<>();

    /**
     * Lista de likes asociados a la publicación.
     */
    @OneToMany(mappedBy = "publicationImg", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImgLike> likes = new ArrayList<>();
}
