package com.repaso.repaso.controller;

import com.repaso.repaso.dto.UserDTO;
import com.repaso.repaso.dto.insertUserDTO;
import com.repaso.repaso.persistence.model.User;
import com.repaso.repaso.services.FollowServiceI;
import com.repaso.repaso.services.SubscripcionServiceI;
import com.repaso.repaso.services.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Controlador para gestionar usuarios.
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    UserServiceI userMngmnt;
    @Autowired
    UserServiceI userService;
    FollowServiceI followService;

    @Autowired
    SubscripcionServiceI SubscripcionService;

    /**
     * Constructor para UserController.
     * @param followService el servicio de seguimiento
     */

    @Autowired
    public UserController(FollowServiceI followService){
        this.followService = followService;

    }


    /**
     * Obtiene todos los usuarios.
     *
     * @return Lista de todos los usuarios en formato UserDTO.
     */
    @GetMapping
    List<UserDTO> getAllUsers() {
        return userMngmnt.searchUserAll();
    }


    /**
     * Obtiene todos los usuarios filtrados por color.
     *
     * @param color el color por el cual filtrar los usuarios
     * @return Lista de usuarios filtrados por color en formato UserDTO.
     */
    @GetMapping("/{color}")
    List<UserDTO> getAllUsersByColor(@PathVariable String color) {
        return userMngmnt.searchUserByColor(color);
    }

    /**
     * Inserta un nuevo usuario.
     *
     * @param iuDTO DTO de inserción del usuario
     */
    @PostMapping("/insert")
    void insertUser(@RequestBody insertUserDTO iuDTO) {
        userMngmnt.addUser(iuDTO);
    }

    /**
     * Realiza un seguimiento a un usuario.
     *
     * @param token   el token de autorización del usuario que realiza el seguimiento
     * @param toUserX el ID del usuario al cual se desea seguir
     * @return ResponseEntity con un valor booleano que indica si se realizó el seguimiento
     */
    @PostMapping("/follow/{toUserX}")
    public ResponseEntity<Boolean> Like(@RequestHeader("Authorization") String token, @PathVariable  String toUserX) {

        Boolean follow = followService.FollowUser(token,toUserX);
        return new ResponseEntity<>(follow, HttpStatus.OK);

    }
    /**
     * Obtiene los IDs de los usuarios seguidos por el usuario autenticado.
     *
     * @param jwt el token de autorización del usuario
     * @return Lista de IDs de los usuarios seguidos
     */
    @GetMapping("/seguidos")
    public List<String> Seguidos (@RequestHeader("Authorization") String jwt) {

        return userService.getFollowedIdsByFollowerId(jwt);

    }
    /**
     * Obtiene la información del usuario autenticado.
     *
     * @param jwt el token de autorización del usuario
     * @return DTO con la información del usuario autenticado
     */
    @GetMapping("/info")
    public UserDTO MyInfo (@RequestHeader("Authorization") String jwt) {

        System.out.println("ENTRO EN EL ENDPOINT");
        return userService.searchUserToken(jwt);

    }
    /**
     * Obtiene la información de un usuario por su ID.
     *
     * @param id el ID del usuario
     * @return DTO con la información del usuario encontrado
     */
    @GetMapping("/infoId/{id}")
    public UserDTO infoUserByID (@PathVariable  String id) {

        return userService.searchUserById(id);

    }
    /**
     * Obtiene la información de un usuario por su nombre.
     *
     * @param nombre el nombre del usuario
     * @return DTO con la información del usuario encontrado
     */
    @GetMapping("/infoUser/{nombre}")
    public UserDTO infoUserByName (@PathVariable  String nombre) {
        return userService.searchUserByName(nombre);
    }

    @PostMapping("/infoUser/socket/{id}/{socket}")
    public Integer socketPost (@PathVariable  String id ,@PathVariable  String socket) {
        return userService.updateSocket(id,socket);
    }

    @PostMapping("/infoUser/socket/{id}/delete")
    public Integer socketDelete (@PathVariable String id) {
        return userService.updateSocketDelete(id);
    }

    @GetMapping("/idBySocket/{socket}")
    public String idBySocket (@PathVariable String socket) {
        return userService.searchIdBySocket(socket);
    }

    @GetMapping("/SocketById/{id}")
    public String socketById (@PathVariable String id) {
        return userService.searchSocketById(id);
    }
    /**
     * Cambia la imagen de perfil de un usuario.
     *
     * @param file  el archivo de imagen
     * @param token el token de autorización del usuario
     * @return ResponseEntity con un mensaje indicando el estado de la operación
     */
    @PostMapping("/changeImgProfile")
    public ResponseEntity<String> changeImgProfile(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token) {
        try {
            System.out.println("entra1");
            userService.changeImgProfile(file,token);
            System.out.println("entra1");
            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }
    /**
     * Verifica si un usuario sigue a otro usuario.
     *
     * @param token   el token de autorización del usuario
     * @param toUserX el ID del usuario al cual se desea seguir
     * @return ResponseEntity con un valor booleano que indica si el usuario sigue al otro
     */
    @GetMapping("/followExist/{toUserX}")
    public ResponseEntity<Boolean> existeFollow (@RequestHeader("Authorization") String token, @PathVariable  String toUserX) {

        Boolean follow = followService.followExist(token,toUserX);
        return new ResponseEntity<>(follow, HttpStatus.OK);

    }


    /**
     * Verifica si un usuario tiene una subscripción activa a otro usuario.
     *
     * @param token   el token de autorización del usuario
     * @param toUserX el ID del usuario al cual se desea subscribir
     * @return ResponseEntity con un valor booleano que indica si la subscripción existe
     */
    @GetMapping("/SubscripcionExist/{toUserX}")
    public ResponseEntity<Boolean> existeSubscripcion (@RequestHeader("Authorization") String token, @PathVariable  String toUserX) {

        Boolean Subscripcion = SubscripcionService.SubscribeExist(token,toUserX);
        return new ResponseEntity<>(Subscripcion, HttpStatus.OK);

    }
    /**
     * Subscripción a un usuario.
     *
     * @param token   el token de autorización del usuario
     * @param toUserX el ID del usuario al cual se desea subscribir
     * @return ResponseEntity con un valor booleano que indica si se realizó la subscripción
     */
    @PostMapping("/Subscribirse/{toUserX}")
    public ResponseEntity<Boolean> Subscribirse (@RequestHeader("Authorization") String token, @PathVariable  String toUserX) {

        Boolean Subscripcion = SubscripcionService.SubscribeUser(token,toUserX);
        return new ResponseEntity<>(Subscripcion, HttpStatus.OK);

    }
    /**
     * Obtiene la cantidad de usuarios seguidos por un usuario.
     *
     * @param id el ID del usuario
     * @return la cantidad de usuarios seguidos por el usuario
     */
    @GetMapping("/sigues/{id}")
    public int sigues (@PathVariable Long id) {
        return followService.seguidos(id);
    }

    /**
     * Obtiene la cantidad de seguidores de un usuario.
     *
     * @param id el ID del usuario
     * @return la cantidad de seguidores del usuario
     */
    @GetMapping("/seguidores/{id}")
    public int seguidores (@PathVariable Long id) {
        return followService.seguidores(id);
    }


    /**
     * Obtiene las subscripciones de un usuario.
     *
     * @param jwt el token de autorización del usuario
     * @return Lista de IDs de los usuarios a los que está subscripto el usuario
     */
    @GetMapping("/subscripciones")
    public List<String> subscripciones (@RequestHeader("Authorization") String jwt) {

        return userService.getSubscriptionsByTokern(jwt);

    }
    /**
     * Obtiene información de múltiples usuarios por sus IDs.
     *
     * @param userIds lista de IDs de usuarios
     * @return Lista de DTOs con la información de los usuarios encontrados
     */
    @GetMapping("/users/subscripted")
    public List<UserDTO> getUserSubscritos(@RequestBody List<String> userIds) {
        return userIds.stream()
                .map(userService::searchUserById)
                .collect(Collectors.toList());
    }



}
