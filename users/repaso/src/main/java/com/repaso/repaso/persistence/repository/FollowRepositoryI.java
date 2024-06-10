package com.repaso.repaso.persistence.repository;

import com.repaso.repaso.persistence.model.Follows;
import com.repaso.repaso.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio para la entidad Follows.
 */
@Repository
public interface FollowRepositoryI extends JpaRepository<Follows, Long> {


    /**
     * Busca una entrada de seguimiento por el usuario seguidor y el usuario seguido.
     *
     * @param follower el usuario que sigue
     * @param followed el usuario seguido
     * @return la entrada de seguimiento encontrada, o null si no existe
     */
    Follows findByFollowerAndFollowed(User follower, User followed);
    /**
     * Cuenta la cantidad de usuarios seguidos por un usuario específico.
     *
     * @param id el ID del usuario
     * @return el número de usuarios seguidos por el usuario con el ID dado
     */
    @Query(value = "SELECT count(*) FROM usersdb.follows where follower_id= :id", nativeQuery = true)
    int seguidos(@Param("id") Long id);
    /**
     * Cuenta la cantidad de seguidores de un usuario específico.
     *
     * @param id el ID del usuario
     * @return el número de seguidores del usuario con el ID dado
     */
    @Query(value = "SELECT count(*) FROM usersdb.follows where followed_id= :id", nativeQuery = true)
    int seguidores(@Param("id") Long id);


}
