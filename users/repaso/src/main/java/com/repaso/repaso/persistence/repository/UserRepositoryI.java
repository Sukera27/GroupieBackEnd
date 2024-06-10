package com.repaso.repaso.persistence.repository;

import com.repaso.repaso.dto.UserDTO;
import com.repaso.repaso.persistence.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Repositorio para la entidad User.
 */
@Repository
public interface UserRepositoryI extends JpaRepository<User, Long> {
    /**
     * Encuentra usuarios por su color favorito.
     *
     * @param color Color favorito a buscar.
     * @return Lista de usuarios con el color favorito especificado.
     */
    List<User> findUserByFavoriteColor(String color);
    /**
     * Encuentra un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return Usuario encontrado, encapsulado en un Optional.
     */
    Optional<User> findByUsername(String username);

    default User getByUsername(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
    /**
     * Obtiene un usuario por su ID de usuario.
     *
     * @param id ID del usuario a buscar.
     * @return Usuario encontrado.
     */
    User findByUserId(Long id);
    /**
     * Encuentra los IDs de los usuarios seguidos por un usuario dado.
     *
     * @param id ID del usuario seguidor.
     * @return Lista de IDs de usuarios seguidos.
     */
    @Query(value = "SELECT FOLLOWED_ID FROM usersdb.follows WHERE follower_id = :id", nativeQuery = true)
    List<String> findFollowedIdsByFollowerId(@Param("id") Long id);

    /**
     * Actualiza el socket de un usuario.
     *
     * @param id     ID del usuario cuyo socket se va a actualizar.
     * @param socket Nuevo valor del socket.
     * @return Número de filas afectadas por la actualización.
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE usersdb.usuarios u SET u.socketusuario = :socket WHERE u.usuario_id = :id", nativeQuery = true)
    Integer updateSocket(@Param("id") Long id, @Param("socket") String socket);

    /**
     * Elimina el socket de un usuario.
     *
     * @param id ID del usuario cuyo socket se va a eliminar.
     * @return Número de filas afectadas por la actualización.
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE usersdb.usuarios u SET u.socketusuario = null WHERE u.usuario_id = :id", nativeQuery = true)
    Integer updateSocketDelete(@Param("id") Long id);
    /**
     * Encuentra un usuario por su socket.
     *
     * @param socket Socket del usuario a buscar.
     * @return Usuario encontrado.
     */
    User findBySocketusuario(String socket);
    /**
     * Encuentra los IDs de los usuarios a los que un usuario dado está subscripto.
     *
     * @param id ID del usuario subscriptor.
     * @return Lista de IDs de usuarios subscriptos.
     */
    @Query(value = "SELECT subscripted_id FROM usersdb.subscriptions where subscriber_id = :id", nativeQuery = true)
    List<String> findSubscriptionsByFollowerId(@Param("id") Long id);

    /*@Modifying
    @Transactional
    @Query(value = "UPDATE repaso_spring.usuarios u SET u.socketusuario = null WHERE u.usuario_id = :id", nativeQuery = true)
    Integer updateImgProfile(@Param("id") Long id);*/
}
