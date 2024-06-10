package com.repaso.repaso.persistence.repository;

import com.repaso.repaso.persistence.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para la entidad Rol.
 */
@Repository
public interface RolRepositoryI extends JpaRepository<Rol, Long> {

}
