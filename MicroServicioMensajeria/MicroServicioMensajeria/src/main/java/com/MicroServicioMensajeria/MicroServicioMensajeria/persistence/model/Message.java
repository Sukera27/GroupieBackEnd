package com.MicroServicioMensajeria.MicroServicioMensajeria.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Entidad que representa un mensaje en la base de datos.
 */
@Entity
@Table(name="RS_MESSAGE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    /**
     * Identificador único del mensaje.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RS_MESSAGE_ID")
    private Long messageId;
    /**
     * Identificador del usuario que envía el mensaje.
     */
    @Column(name = "RS_USER_ID")
    private Long userId;
    /**
     * Identificador del destinatario del mensaje.
     */
    @Column(name = "RS_DESTINATARIO_ID")
    private Long destinatarioId;
    /**
     * Contenido del mensaje.
     * Almacena texto en formato largo.
     */
    @Lob
    @Column(name = "RS_CONTENIDO", columnDefinition = "TEXT")
    private String contenido;
    /**
     * Marca de tiempo cuando el mensaje fue creado.
     */
    @Column(name = "MESSAGE_TIMESTAMP", columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;
}
