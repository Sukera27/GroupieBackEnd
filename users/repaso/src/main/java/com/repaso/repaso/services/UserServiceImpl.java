package com.repaso.repaso.services;

import com.repaso.repaso.dto.UserDTO;
import com.repaso.repaso.dto.insertUserDTO;
import com.repaso.repaso.persistence.model.Rol;
import com.repaso.repaso.persistence.model.User;
import com.repaso.repaso.persistence.repository.RolRepositoryI;
import com.repaso.repaso.persistence.repository.UserRepositoryI;
import com.repaso.repaso.security.auth.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * Implementación del servicio UserServiceI que maneja las operaciones relacionadas con los usuarios.
 */
@Service
public class UserServiceImpl implements UserServiceI{

    @Autowired
    UserRepositoryI userRepo;
    @Autowired
    JWTService jwtService;

    @Autowired
    RolRepositoryI rolRepo;
    /**
     * Busca todos los usuarios y devuelve una lista de UserDTO.
     *
     * @return Lista de UserDTO
     */
    @Override
    public List<UserDTO> searchUserAll() {
        List<UserDTO> usersDTO = new ArrayList<>();
        List<User> users = userRepo.findAll();
        for (User u : users) {
            usersDTO.add(new UserDTO(u));
        }
        return usersDTO;
    }
    /**
     * Busca usuarios por color favorito y devuelve una lista de UserDTO.
     *
     * @param color Color favorito de los usuarios a buscar
     * @return Lista de UserDTO
     */
    @Override
    public List<UserDTO> searchUserByColor(String color) {
        List<UserDTO> usersDTO = new ArrayList<>();
        List<User> users = userRepo.findUserByFavoriteColor(color);
        for (User u : users) {
            usersDTO.add(new UserDTO(u));
        }
        return usersDTO;
    }
    /**
     * Agrega un nuevo usuario con los datos proporcionados en insertUserDTO.
     *
     * @param iuDTO Datos del nuevo usuario
     */
    @Override
    public void addUser(insertUserDTO iuDTO) {
        User u = new User();
        u.setUsername(iuDTO.getName());
        u.setPassword(iuDTO.getPassword());
        u.setFavoriteColor(iuDTO.getColor());
        Optional<Rol> uOpt = rolRepo.findById(2L);
        if (uOpt.isPresent()) {
            u.setPermise(uOpt.get());
        }
        userRepo.save(u);
    }
    /**
     * Busca un usuario por el token JWT y devuelve un UserDTO correspondiente.
     *
     * @param jwt Token JWT del usuario
     * @return UserDTO del usuario encontrado
     */
    @Override
    public UserDTO searchUserToken(String jwt) {

        System.out.println("ENTRO EN EL METODO");

        Map<String, String> userIdAndUsername = jwtService.getId(jwt);
        String userId = userIdAndUsername.get("userId");

        System.out.println("TOKEN DECODIFICADO");

        Long userId1 = Long.valueOf(userId);

        System.out.println(userId1);

        User user = userRepo.findByUserId(userId1);

        // Convertir User a UserDTO
        UserDTO userDTO = new UserDTO(user);

        return userDTO;
    }
    /**
     * Busca un usuario por su ID y devuelve un UserDTO correspondiente.
     *
     * @param id ID del usuario a buscar
     * @return UserDTO del usuario encontrado
     */
    @Override
    public UserDTO searchUserById(String id) {

        System.out.println("ENTRO EN EL METODO");

        Long userId1 = Long.valueOf(id);

        User user = userRepo.findByUserId(userId1);

        UserDTO userDTO = new UserDTO(user);

        return userDTO;
    }
    /**
     * Busca un usuario por su nombre de usuario y devuelve un UserDTO correspondiente.
     *
     * @param username Nombre de usuario del usuario a buscar
     * @return UserDTO del usuario encontrado
     */
    @Override
    public UserDTO searchUserByName(String username) {

        User user = userRepo.getByUsername(username);
        UserDTO userDTO = new UserDTO(user);

        return userDTO;

    }

    /**
     * Obtiene los IDs de los usuarios seguidos por el usuario autenticado mediante el token JWT.
     *
     * @param jwt Token JWT del usuario autenticado
     * @return Lista de IDs de usuarios seguidos
     */
    @Override
    public List<String> getFollowedIdsByFollowerId(String jwt) {

        Map<String, String> userIdAndUsername = jwtService.getId(jwt);
        String userId = userIdAndUsername.get("userId");

        Long userId1 = Long.valueOf(userId);
        return userRepo.findFollowedIdsByFollowerId(userId1);

    }

    @Transactional
    @Override
    public Integer updateSocket(String id, String socket) {

        Long userId1 = Long.valueOf(id);
        return userRepo.updateSocket(userId1,socket);

    }

    @Override
    public Integer updateSocketDelete(String id) {

        Long userId1 = Long.valueOf(id);
        return userRepo.updateSocketDelete(userId1);
    }

    @Override
    public String searchIdBySocket(String socket) {

        UserDTO userDTO = new UserDTO(userRepo.findBySocketusuario(socket));
        return userDTO.getId().toString();

    }

    @Override
    public String searchSocketById(String id) {

        UserDTO userDTO = new UserDTO(userRepo.findByUserId(Long.valueOf(id)));
        return userDTO.getSocket().toString();

    }
    /**
     * Cambia la imagen de perfil de un usuario.
     *
     * @param file  Archivo de imagen
     * @param token Token JWT del usuario
     * @return Usuario con la imagen de perfil actualizada
     * @throws IOException Si ocurre un error al procesar el archivo
     */
    @Override
    public User changeImgProfile(MultipartFile file, String token) throws IOException {

        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        Long userId1 = Long.valueOf(userId);
        System.out.println("si entra aqui");
        User user = userRepo.findByUserId(userId1);
        user.setImagePerfil(file.getBytes());
        return userRepo.save(user);

    }

    /**
     * Obtiene los IDs de los usuarios a los que está suscrito el usuario autenticado mediante el token JWT.
     *
     * @param jwt Token JWT del usuario autenticado
     * @return Lista de IDs de usuarios suscritos
     */
    @Override
    public List<String> getSubscriptionsByTokern(String jwt) {

        Map<String, String> userIdAndUsername = jwtService.getId(jwt);
        String userId = userIdAndUsername.get("userId");

        Long userId1 = Long.valueOf(userId);
        return userRepo.findSubscriptionsByFollowerId(userId1);

    }

}
