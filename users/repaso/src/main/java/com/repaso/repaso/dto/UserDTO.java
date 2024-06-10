package com.repaso.repaso.dto;

import com.repaso.repaso.persistence.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class UserDTO {
    String name;

    String descripcion;
    Long id;
    String rolname;

    String Socket;

    byte[] data;


    public UserDTO(User user) {
        this.name = user.getUsername();
        this.rolname = user.getPermise().getRolName();
        this.id = user.getUserId();
        this.Socket = user.getSocketusuario();
        this.data = user.getImagePerfil();
    }

}
