package com.MicroServicioMensajeria.MicroServicioMensajeria.service;

import com.MicroServicioMensajeria.MicroServicioMensajeria.persistence.model.Message;
import com.MicroServicioMensajeria.MicroServicioMensajeria.persistence.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
/**
 * Implementación del servicio de gestión de mensajes.
 * Proporciona métodos para guardar y recuperar mensajes.
 */
@Service
public class MessageServiceImpl implements MessageServiceI{
    @Autowired
    private JWTImpl jwtService;

    @Autowired
    private MessageRepository messageRepository;
    /**
     * Guarda un nuevo mensaje.
     *
     * @param token el token de autorización del usuario que envía el mensaje
     * @param destinatarioId el ID del destinatario del mensaje
     * @param contenido el contenido del mensaje
     * @return el mensaje guardado
     */
    @Override
    public Message saveMessage2(String token,String destinatarioId,String contenido){
        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        Long sender = Long.valueOf(userId);
        Long render = Long.valueOf(destinatarioId);
        Message msg = new Message();
        msg.setContenido(contenido);
        msg.setUserId(sender);
        msg.setDestinatarioId(render);
        msg.setTimestamp(LocalDateTime.now());

      return messageRepository.save(msg);
    }
    /**
     * Encuentra los mensajes enviados por un usuario a un destinatario específico, ordenados por fecha de creación descendente.
     *
     * @param token el token de autorización del usuario
     * @param destinatarioId el ID del destinatario del mensaje
     * @return lista de mensajes ordenados por fecha de creación descendente
     */
    @Override
    public List<Message> findByUserIdAndDestinatarioIdOrderByTimestampDesc(String token,String destinatarioId) {
        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        Long sender = Long.valueOf(userId);
        Long render = Long.valueOf(destinatarioId);

        return messageRepository.findByUserIdAndDestinatarioIdOrderByTimestampDesc(sender,render);
    }
    /**
     * Obtiene el historial de mensajes entre el usuario autenticado y un destinatario específico.
     *
     * @param token el token de autorización del usuario
     * @param idDestinatario el ID del destinatario
     * @return lista de mensajes en el historial
     */
    @Override
    public List<Message> historial(String token, String idDestinatario) {

        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        Long sender = Long.valueOf(userId);
        Long render = Long.valueOf(idDestinatario);


        return messageRepository.findMessagesBetweenUsers(sender,render);
    }
    /**
     * Obtiene una lista de IDs de usuarios con los que el usuario autenticado ha intercambiado mensajes.
     *
     * @param token el token de autorización del usuario
     * @return lista de IDs de usuarios
     */
    @Override
    public List<String> userMessages(String token) {
        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        Long sender = Long.valueOf(userId);

        return messageRepository.userMessages(sender);
    }


}
