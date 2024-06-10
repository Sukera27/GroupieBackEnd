package com.repaso.repaso.services;

import com.repaso.repaso.persistence.model.Rol;
/**
 * Interfaz para el servicio de roles.
 */
public interface RolServiceI {
    /**
     * Guarda un rol en la base de datos.
     *
     * @param rol Rol a guardar.
     */
    void saveRol(Rol rol);
}
