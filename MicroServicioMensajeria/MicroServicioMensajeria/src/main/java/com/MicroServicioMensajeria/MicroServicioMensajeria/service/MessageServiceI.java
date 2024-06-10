package com.MicroServicioMensajeria.MicroServicioMensajeria.service;

import com.MicroServicioMensajeria.MicroServicioMensajeria.persistence.model.Message;

import java.util.List;
/**
 * Interfaz para el servicio de gestión de mensajes.
 * Proporciona métodos para guardar y recuperar mensajes.
 */
public interface MessageServiceI {
    /**
     * Guarda un nuevo mensaje.
     *
     * @param token el token de autorización del usuario que envía el mensaje
     * @param destinatarioId el ID del destinatario del mensaje
     * @param contenido el contenido del mensaje
     * @return el mensaje guardado
     */
    public Message saveMessage2(String token, String destinatarioId, String contenido);
    /**
     * Encuentra los mensajes enviados por un usuario a un destinatario específico, ordenados por fecha de creación descendente.
     *
     * @param token el token de autorización del usuario
     * @param destinatarioId el ID del destinatario del mensaje
     * @return lista de mensajes ordenados por fecha de creación descendente
     */
    public List<Message> findByUserIdAndDestinatarioIdOrderByTimestampDesc(String token, String destinatarioId);
    /**
     * Obtiene el historial de mensajes entre el usuario autenticado y un destinatario específico.
     *
     * @param token el token de autorización del usuario
     * @param idDestinatario el ID del destinatario
     * @return lista de mensajes en el historial
     */
    public List<Message> historial(String token, String idDestinatario);
    /**
     * Obtiene una lista de IDs de usuarios con los que el usuario autenticado ha intercambiado mensajes.
     *
     * @param token el token de autorización del usuario
     * @return lista de IDs de usuarios
     */
    public List<String> userMessages(String token);


}
