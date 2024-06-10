package com.repaso.repaso.security.auth.controller;

import com.repaso.repaso.dto.UserDTO;
import com.repaso.repaso.security.auth.modeldto.AuthResponseDTO;
import com.repaso.repaso.security.auth.modeldto.LoginRequestDTO;
import com.repaso.repaso.security.auth.modeldto.RegisterRequestDTO;
import com.repaso.repaso.security.auth.service.AuthServiceI;
import com.repaso.repaso.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Controlador que maneja las operaciones de autenticación y autorización.
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthServiceI authService;
    /**
     * Endpoint para iniciar sesión.
     * @param request Datos de inicio de sesión.
     * @return Respuesta de autenticación.
     */
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     * @param request Datos de registro del usuario.
     * @return Respuesta de autenticación.
     */
    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }
    /**
     * Endpoint para cambiar la imagen de perfil del usuario.
     * @param file Archivo de imagen.
     * @param token Token de autorización.
     * @return Respuesta de éxito o error.
     */
    @PostMapping("/changeImgProfile")
    public ResponseEntity<String> changeImgProfile(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token) {
        try {
            System.out.println("entra1");
            authService.changeImgProfile(file,token);
            System.out.println("entra1");
            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }
    @Autowired
    UserServiceI userService;

    /**
     * Endpoint para obtener usuarios subscriptos.
     * @param userIds Lista de identificadores de usuario.
     * @return Lista de usuarios encontrados.
     */
    @PostMapping("/users/subscripted")
    public List<UserDTO> getUserSubscritos(@RequestBody List<String> userIds) {
        return userIds.stream()
                .map(userService::searchUserById)
                .collect(Collectors.toList());
    }
    /**
     * Endpoint para obtener mensajes de usuarios.
     * @param userIds Lista de identificadores de usuario.
     * @return Lista de usuarios encontrados.
     */
    @PostMapping("/users/mennsage")
    public List<UserDTO> getUserMessage(@RequestBody List<String> userIds) {
        return userIds.stream()
                .map(userService::searchUserById)
                .collect(Collectors.toList());
    }

}