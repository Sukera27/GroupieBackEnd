package com.repaso.repaso.services;

import com.repaso.repaso.dto.UserDTO;
import com.repaso.repaso.dto.insertUserDTO;
import com.repaso.repaso.persistence.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
/**
 * Interfaz que define los servicios relacionados con los usuarios.
 */
public interface UserServiceI {
    /**
     * Busca todos los usuarios y los convierte en DTOs.
     *
     * @return Lista de UserDTO.
     */
    List<UserDTO> searchUserAll();
    /**
     * Busca usuarios por color favorito y los convierte en DTOs.
     *
     * @param color Color favorito a buscar.
     * @return Lista de UserDTO.
     */
    List<UserDTO> searchUserByColor(String color);
    /**
     * Agrega un nuevo usuario.
     *
     * @param iuDTO DTO con la información del usuario a agregar.
     */
    void addUser(insertUserDTO iuDTO);
    /**
     * Busca y devuelve la información del usuario autenticado a partir de su JWT.
     *
     * @param jwt Token JWT del usuario autenticado.
     * @return UserDTO con la información del usuario.
     */
    UserDTO searchUserToken(String jwt);
    /**
     * Busca y devuelve la información de un usuario por su ID.
     *
     * @param id ID del usuario a buscar.
     * @return UserDTO con la información del usuario.
     */
    UserDTO searchUserById(String id);
    /**
     * Busca y devuelve la información de un usuario por su nombre.
     *
     * @param id Nombre del usuario a buscar.
     * @return UserDTO con la información del usuario.
     */
    UserDTO searchUserByName(String id);
    /**
     * Obtiene los IDs de los usuarios seguidos por un usuario a partir de su JWT.
     *
     * @param jwt Token JWT del usuario autenticado.
     * @return Lista de IDs de usuarios seguidos.
     */
    public List<String> getFollowedIdsByFollowerId(String jwt);

    public Integer updateSocket(String jwt, String socket);

    public Integer updateSocketDelete(String jwt);

    String searchIdBySocket(String socket);

    String searchSocketById(String id);
    /**
     * Cambia la imagen de perfil de un usuario.
     *
     * @param file  Archivo de imagen.
     * @param token Token JWT del usuario autenticado.
     * @return Usuario con la nueva imagen de perfil.
     * @throws IOException Si ocurre un error al procesar el archivo.
     */
    User changeImgProfile(MultipartFile file, String token) throws IOException;
    /**
     * Obtiene las suscripciones de un usuario a partir de su JWT.
     *
     * @param jwt Token JWT del usuario autenticado.
     * @return Lista de IDs de usuarios a los que el usuario está suscrito.
     */
    public List<String> getSubscriptionsByTokern(String jwt);


}
