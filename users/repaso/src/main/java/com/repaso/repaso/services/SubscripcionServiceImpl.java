package com.repaso.repaso.services;

import com.repaso.repaso.persistence.model.Follows;
import com.repaso.repaso.persistence.model.Subscripcion;
import com.repaso.repaso.persistence.model.User;
import com.repaso.repaso.persistence.repository.FollowRepositoryI;
import com.repaso.repaso.persistence.repository.SubscriptionsRepositoryI;
import com.repaso.repaso.security.auth.service.JWTService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
/**
 * Implementación del servicio de suscripciones.
 */
@Service
public class SubscripcionServiceImpl implements SubscripcionServiceI{

    private final SubscriptionsRepositoryI subsRepo;
    private final JWTService jwtService;

    public SubscripcionServiceImpl(SubscriptionsRepositoryI subsRepo, JWTService jwtService) {
        this.subsRepo = subsRepo;
        this.jwtService = jwtService;
    }

    /**
     * Suscribe al usuario identificado por `UserToFollow` al usuario autenticado por `JWT`.
     *
     * @param JWT          Token de autenticación del usuario que realiza la suscripción.
     * @param UserToFollow Identificador del usuario al que se va a suscribir.
     * @return `true` si la suscripción se realizó con éxito, `false` si la suscripción ya existía y se eliminó.
     */
    @Override
    public Boolean SubscribeUser(String JWT, String UserToFollow) {
        Map<String, String> userIdAndUsername = jwtService.getId(JWT);
        String userId = userIdAndUsername.get("userId");

        Long userId1 = Long.valueOf(userId);
        Long userToFollowId1 = Long.valueOf(UserToFollow);

        User sigue = new User();
        User seguido = new User();

        sigue.setUserId(userId1);
        seguido.setUserId(userToFollowId1);

        Subscripcion followRelationship = subsRepo.findBySubscriberAndSubscripted(sigue, seguido);


        if (followRelationship != null) {
            subsRepo.delete(followRelationship);
            return false;
        } else {
            followRelationship = new Subscripcion();

            LocalDateTime fechaActual = LocalDateTime.now();
            LocalDateTime fechaActualMasDosHoras = fechaActual.plusHours(2);

            followRelationship.setSubscriber(sigue);
            followRelationship.setSubscripted(seguido);
            followRelationship.setCreationDate(fechaActualMasDosHoras);
            subsRepo.save(followRelationship);
            return true;

        }
    }
    /**
     * Verifica si el usuario autenticado por `JWT` está suscrito al usuario identificado por `UserToFollow`.
     *
     * @param JWT          Token de autenticación del usuario que realiza la verificación.
     * @param UserToFollow Identificador del usuario al que se va a verificar la suscripción.
     * @return `true` si el usuario autenticado está suscrito al usuario especificado, `false` si no lo está.
     */
    @Override
    public Boolean SubscribeExist(String JWT, String UserToFollow) {

        Map<String, String> userIdAndUsername = jwtService.getId(JWT);
        String userId = userIdAndUsername.get("userId");

        Long userId1 = Long.valueOf(userId);
        Long userToFollowId1 = Long.valueOf(UserToFollow);

        User sigue = new User();
        User seguido = new User();

        sigue.setUserId(userId1);
        seguido.setUserId(userToFollowId1);

        Subscripcion followRelationship = subsRepo.findBySubscriberAndSubscripted(sigue, seguido);

        if (followRelationship != null) {
            return true;
        } else {
            return false;
        }
    }




}
