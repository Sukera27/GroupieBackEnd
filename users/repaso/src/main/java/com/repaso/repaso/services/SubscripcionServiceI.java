package com.repaso.repaso.services;
/**
 * Interfaz para el servicio de suscripciones.
 */
public interface SubscripcionServiceI {
    /**
     * Suscribe al usuario identificado por `UserToFollow` al usuario autenticado por `JWT`.
     *
     * @param JWT          Token de autenticación del usuario que realiza la suscripción.
     * @param UserToFollow Identificador del usuario al que se va a suscribir.
     * @return `true` si la suscripción se realizó con éxito, `false` si la suscripción ya existía y se eliminó.
     */
    Boolean SubscribeUser (String JWT, String UserToFollow);
    /**
     * Verifica si el usuario autenticado por `JWT` está suscrito al usuario identificado por `UserToFollow`.
     *
     * @param JWT          Token de autenticación del usuario que realiza la verificación.
     * @param UserToFollow Identificador del usuario al que se va a verificar la suscripción.
     * @return `true` si el usuario autenticado está suscrito al usuario especificado, `false` si no lo está.
     */
    Boolean SubscribeExist (String JWT, String UserToFollow);

}
