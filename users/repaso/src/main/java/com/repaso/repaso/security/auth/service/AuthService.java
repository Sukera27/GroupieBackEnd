package com.repaso.repaso.security.auth.service;

import com.repaso.repaso.persistence.model.Rol;
import com.repaso.repaso.persistence.model.User;
import com.repaso.repaso.persistence.repository.RolRepositoryI;
import com.repaso.repaso.persistence.repository.UserRepositoryI;
import com.repaso.repaso.security.auth.modeldto.AuthResponseDTO;
import com.repaso.repaso.security.auth.modeldto.LoginRequestDTO;
import com.repaso.repaso.security.auth.modeldto.RegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
/**
 * Implementación del servicio AuthServiceI que maneja las operaciones de autenticación y registro de usuarios.
 */
@Service
public class AuthService implements AuthServiceI {

    @Autowired
    private UserRepositoryI userRepo;

    @Autowired
    private RolRepositoryI rolRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * Método para realizar el inicio de sesión de un usuario.
     *
     * @param request Datos de inicio de sesión.
     * @return Respuesta de autenticación con el token JWT.
     */
    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        User user=userRepo.findByUsername(request.getName()).orElseThrow();
        return new AuthResponseDTO(jwtService.getToken(user));
    }
    /**
     * Método para registrar un nuevo usuario.
     *
     * @param request Datos de registro del usuario.
     * @return Respuesta de autenticación con el token JWT.
     */
    public AuthResponseDTO register(RegisterRequestDTO request) {
        Optional<Rol> rol = rolRepo.findById(2L);
        if (rol.isPresent()) {
            User user = new User();
            user.setUsername(request.getName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setFavoriteColor(request.getColor());
            user.setPermise(rol.get());
            userRepo.save(user);
            return new AuthResponseDTO(jwtService.getToken(user));
        } else {
            return new AuthResponseDTO("");
        }

    }
    /**
     * Método para cambiar la imagen de perfil de un usuario.
     *
     * @param file  Archivo de imagen.
     * @param token Token de autorización.
     * @return Respuesta de éxito o error.
     * @throws IOException Si ocurre un error al procesar el archivo.
     */
    @Override
    public AuthResponseDTO changeImgProfile(MultipartFile file, String token) throws IOException {

        System.out.println("si entra aqui");
        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        Long userId1 = Long.valueOf(userId);
        System.out.println("si entra aqui");
        User user = userRepo.findByUserId(userId1);
        user.setImagePerfil(file.getBytes());
        userRepo.save(user);
        return new AuthResponseDTO("cambio realizado");

    }
}
