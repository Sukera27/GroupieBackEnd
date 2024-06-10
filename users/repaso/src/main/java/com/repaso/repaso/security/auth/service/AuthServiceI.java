package com.repaso.repaso.security.auth.service;

import com.repaso.repaso.security.auth.modeldto.AuthResponseDTO;
import com.repaso.repaso.security.auth.modeldto.LoginRequestDTO;
import com.repaso.repaso.security.auth.modeldto.RegisterRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
 * Interfaz que define los métodos para el servicio de autenticación.
 */
public interface AuthServiceI {
    /**
     * Método para iniciar sesión.
     *
     * @param request Datos de inicio de sesión.
     * @return Respuesta de autenticación con el token JWT.
     */
    AuthResponseDTO login(LoginRequestDTO request);
    /**
     * Método para registrar un nuevo usuario.
     *
     * @param request Datos de registro del usuario.
     * @return Respuesta de autenticación con el token JWT.
     */
    AuthResponseDTO register(RegisterRequestDTO request);
    /**
     * Método para cambiar la imagen de perfil de un usuario.
     *
     * @param file  Archivo de imagen.
     * @param token Token de autorización.
     * @return Respuesta de éxito o error.
     * @throws IOException Si ocurre un error al procesar el archivo.
     */
    AuthResponseDTO changeImgProfile(MultipartFile file, String token) throws IOException;
}
