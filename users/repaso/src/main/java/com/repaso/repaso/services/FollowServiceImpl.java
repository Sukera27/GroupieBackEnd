package com.repaso.repaso.services;

import com.repaso.repaso.persistence.model.Follows;
import com.repaso.repaso.persistence.model.User;
import com.repaso.repaso.persistence.repository.FollowRepositoryI;
import com.repaso.repaso.security.auth.service.JWTService;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
 * Implementación del servicio de gestión de relaciones de seguimiento entre usuarios.
 */
@Service
public class FollowServiceImpl implements FollowServiceI{

    private final FollowRepositoryI followRepo;
    private final JWTService jwtService;
    /**
     * Constructor de la clase FollowServiceImpl.
     *
     * @param followRepo Repositorio de relaciones de seguimiento.
     * @param jwtService Servicio JWT para la autenticación y autorización.
     */
    public FollowServiceImpl(FollowRepositoryI followRepo, JWTService jwtService) {
        this.followRepo = followRepo;
        this.jwtService = jwtService;
    }
    /**
     * Permite a un usuario seguir o dejar de seguir a otro usuario.
     *
     * @param JWT       Token JWT de autenticación del usuario que realiza la acción.
     * @param UserToFollow ID del usuario que se va a seguir/dejar de seguir.
     * @return true si se siguió al usuario, false si se dejó de seguir.
     */
    @Override
    public Boolean FollowUser(String JWT, String UserToFollow) {



        Map<String, String> userIdAndUsername = jwtService.getId(JWT);
        String userId = userIdAndUsername.get("userId");

        Long userId1 = Long.valueOf(userId);
        Long userToFollowId1 = Long.valueOf(UserToFollow);

        User sigue = new User();
        User seguido = new User();

        sigue.setUserId(userId1);
        seguido.setUserId(userToFollowId1);

        Follows followRelationship = followRepo.findByFollowerAndFollowed(sigue, seguido);


        if (followRelationship != null) {
            followRepo.delete(followRelationship);
            return false;
        } else {
            followRelationship = new Follows();


            followRelationship.setFollower(sigue);
            followRelationship.setFollowed(seguido);
            followRepo.save(followRelationship);
            return true;

        }
    }
    /**
     * Verifica si un usuario sigue a otro.
     *
     * @param JWT       Token JWT de autenticación del usuario que realiza la acción.
     * @param UserToFollow ID del usuario a verificar si está siendo seguido.
     * @return true si el usuario está siendo seguido, false si no.
     */

    @Override
    public Boolean followExist(String JWT, String UserToFollow) {
        Map<String, String> userIdAndUsername = jwtService.getId(JWT);
        String userId = userIdAndUsername.get("userId");

        Long userId1 = Long.valueOf(userId);
        Long userToFollowId1 = Long.valueOf(UserToFollow);

        User sigue = new User();
        User seguido = new User();

        sigue.setUserId(userId1);
        seguido.setUserId(userToFollowId1);

        Follows followRelationship = followRepo.findByFollowerAndFollowed(sigue, seguido);


        if (followRelationship != null) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Obtiene el número de usuarios que sigue un usuario dado.
     *
     * @param id ID del usuario del cual se desea obtener el número de seguidos.
     * @return Número de usuarios que sigue el usuario dado.
     */
    @Override
    public int seguidos(Long id) {
        return followRepo.seguidos(id);
    }
    /**
     * Obtiene el número de seguidores de un usuario dado.
     *
     * @param id ID del usuario del cual se desea obtener el número de seguidores.
     * @return Número de seguidores del usuario dado.
     */
    @Override
    public int seguidores(Long id) {
        return followRepo.seguidores(id);
    }


}
