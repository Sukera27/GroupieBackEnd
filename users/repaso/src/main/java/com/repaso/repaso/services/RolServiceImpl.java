package com.repaso.repaso.services;

import com.repaso.repaso.persistence.model.Rol;
import com.repaso.repaso.persistence.repository.RolRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Implementación del servicio de roles.
 */
@Service
public class RolServiceImpl implements RolServiceI{

    @Autowired
    RolRepositoryI rolRepo;

    /**
     * Guarda un rol en la base de datos.
     *
     * @param rol Rol a guardar.
     */
    @Override
    public void saveRol(Rol rol) {
        rolRepo.save(rol);
    }
}
