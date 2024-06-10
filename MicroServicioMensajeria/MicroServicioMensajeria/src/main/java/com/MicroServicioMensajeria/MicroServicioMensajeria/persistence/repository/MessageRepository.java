package com.MicroServicioMensajeria.MicroServicioMensajeria.persistence.repository;


import com.MicroServicioMensajeria.MicroServicioMensajeria.persistence.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio para la entidad Message.
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas sobre los mensajes.
 */
@Repository
public interface  MessageRepository extends JpaRepository<Message, Long> {
    /**
     * Encuentra los mensajes enviados por un usuario a un destinatario específico, ordenados por fecha de creación descendente.
     *
     * @param userId el ID del usuario que envía el mensaje
     * @param destinatarioId el ID del destinatario del mensaje
     * @return lista de mensajes ordenados por fecha de creación descendente
     */
    List<Message> findByUserIdAndDestinatarioIdOrderByTimestampDesc(Long userId,Long destinatarioId);

    /**
     * Encuentra los mensajes intercambiados entre dos usuarios, ordenados por fecha de creación ascendente.
     *
     * @param userId1 el ID del primer usuario
     * @param userId2 el ID del segundo usuario
     * @return lista de mensajes intercambiados entre los dos usuarios
     */
    @Query(value = "SELECT * FROM messagedb.rs_message " +
            "WHERE (rs_destinatario_id = :userId1 AND rs_user_id = :userId2) " +
            "   OR (rs_destinatario_id = :userId2 AND rs_user_id = :userId1) " +
            "ORDER BY message_timestamp ASC", nativeQuery = true)
    List<Message> findMessagesBetweenUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
    /**
     * Encuentra una lista distinta de IDs de usuarios con los que un usuario específico ha intercambiado mensajes.
     *
     * @param userId1 el ID del usuario
     * @return lista de IDs de usuarios con los que el usuario ha intercambiado mensajes
     */
    @Query(value = "SELECT DISTINCT user_id FROM (\n" +
            "    SELECT rs_destinatario_id AS user_id FROM messagedb.rs_message WHERE rs_user_id = :userId\n" +
            "    UNION\n" +
            "    SELECT rs_user_id AS user_id FROM messagedb.rs_message WHERE rs_destinatario_id = :userId\n" +
            ") AS combined_users;", nativeQuery = true)
    List<String> userMessages(@Param("userId") Long userId1);

}
