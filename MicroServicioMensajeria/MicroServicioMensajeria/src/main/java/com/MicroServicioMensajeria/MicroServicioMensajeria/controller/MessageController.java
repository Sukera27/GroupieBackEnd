package com.MicroServicioMensajeria.MicroServicioMensajeria.controller;

import com.MicroServicioMensajeria.MicroServicioMensajeria.persistence.model.Message;
import com.MicroServicioMensajeria.MicroServicioMensajeria.persistence.repository.MessageRepository;
import com.MicroServicioMensajeria.MicroServicioMensajeria.service.MessageServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Controlador para gestionar mensajes.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    private MessageServiceI messageServiceI;

    /**
     * Constructor para MessageController.
     * @param messageService el servicio de mensajes
     */
    @Autowired
    public MessageController(MessageServiceI messageService) {
        this.messageServiceI = messageService;
    }
    /**
     * Guardar un nuevo mensaje.
     * @param message el mensaje a guardar
     * @return el mensaje guardado
     */
    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }
    /**
     * Guardar un nuevo mensaje con detalles proporcionados en los headers.
     * @param token el token de autorización
     * @param contenido el contenido del mensaje
     * @param destinatarioId el ID del destinatario
     * @return el mensaje guardado
     */
    @PostMapping("/POST")
    public Message saveMessage2(@RequestHeader("Authorization") String token,@RequestHeader("Contenido") String contenido,@RequestHeader("Destinatario") String destinatarioId ) {

        return messageServiceI.saveMessage2(token,destinatarioId,contenido);
    }

    /**
     * Obtener los mensajes enviados a un destinatario específico.
     * @param token el token de autorización
     * @param destinatarioId el ID del destinatario
     * @return lista de mensajes ordenados por fecha de creación descendente
     */
    @GetMapping("/user/destinatario/{destinatarioId}")
    public List<Message> getMessages(@RequestHeader("Authorization") String token, @PathVariable String destinatarioId) {
        return messageServiceI.findByUserIdAndDestinatarioIdOrderByTimestampDesc(token, destinatarioId);
    }
    /**
     * Obtener todos los mensajes.
     * @return lista de todos los mensajes
     */
    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    /**
     * Obtener el historial de mensajes con un destinatario específico.
     * @param token el token de autorización
     * @param idDestinatario el ID del destinatario
     * @return lista de mensajes en el historial
     */
    @GetMapping("/user/{idDestinatario}")
    public List<Message> getHistorial(@RequestHeader("Authorization") String token, @PathVariable String idDestinatario) {
        return messageServiceI.historial(token, idDestinatario);
    }
    /**
     * Obtener la lista de IDs de usuarios con los que el usuario autenticado ha intercambiado mensajes.
     * @param token el token de autorización
     * @return lista de IDs de usuarios
     */
    @GetMapping("/user/messages")
    public List<String> getUserMessageList(@RequestHeader("Authorization") String token) {
        return messageServiceI.userMessages(token);
    }

}