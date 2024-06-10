package com.repaso.repaso.services;

import com.repaso.repaso.dto.UserDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowServiceI {

    /**
     * Método para seguir a un usuario.
     *
     * @param JWT          Token de autenticación del usuario que realiza la acción.
     * @param UserToFollow ID o nombre de usuario del usuario a seguir.
     * @return true si la operación fue exitosa, false si falló.
     */
    Boolean FollowUser (String JWT, String UserToFollow);
    /**
     * Método para verificar si un usuario sigue a otro.
     *
     * @param JWT          Token de autenticación del usuario que realiza la acción.
     * @param UserToFollow ID o nombre de usuario del usuario a verificar si es seguido.
     * @return true si el usuario está siguiendo a UserToFollow, false si no.
     */
    Boolean followExist (String JWT, String UserToFollow);
    /**
     * Método para obtener la cantidad de usuarios que sigue un usuario.
     *
     * @param id ID del usuario del cual se desea obtener la cantidad de seguidos.
     * @return Cantidad de usuarios que sigue el usuario con el ID especificado.
     */
    int seguidos(Long id);
    /**
     * Método para obtener la cantidad de seguidores de un usuario.
     *
     * @param id ID del usuario del cual se desea obtener la cantidad de seguidores.
     * @return Cantidad de seguidores del usuario con el ID especificado.
     */
    int seguidores(Long id);

}
