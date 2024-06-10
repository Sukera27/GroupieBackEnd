package com.repaso.repaso.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * Entidad que representa una suscripción en la base de datos.
 */
@Entity
@Table(name="Subscriptions")
@Data
@NoArgsConstructor
public class Subscripcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Subscriptions_id")
    private Long followId;
    /** Usuario que realiza la suscripción. */
    @ManyToOne
    @JoinColumn(name = "Subscriber_id", referencedColumnName = "usuario_id")
    private User subscriber;
    /** Usuario que es suscrito. */
    @ManyToOne
    @JoinColumn(name = "Subscripted_id", referencedColumnName = "usuario_id")
    private User subscripted;

    /** Fecha y hora de creación de la suscripción. */
    @Column(name = "RS_PUBLICATION_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;
}
