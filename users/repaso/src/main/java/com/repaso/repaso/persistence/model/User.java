/*package com.repaso.repaso.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "seguidos"})
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    Long userId;

    @Column(name = "nombre", unique = true)
    String username;

    @Column(name = "contrasenha")
    String password;

    @Column(name = "color_favorito")
    String favoriteColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permiso", referencedColumnName = "rol_id")
    @JsonIgnoreProperties("usuarios") // Evitar serialización recursiva con Rol
    Rol permise;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("follower") // Evitar serialización recursiva con Follows
    List<Follows> seguidos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


*/

































package com.repaso.repaso.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
/**
 * Entidad que representa a un usuario en la base de datos.
 */
@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    Long userId;
    /** Nombre de usuario único. */
    @Column(name = "nombre", unique = true)
    String username;

    @Column(name = "socketusuario", unique = true)
    String socketusuario;

    /** Contraseña del usuario. */
    @Column(name = "contrasenha")
    String password;

    /** Color favorito del usuario. */
    @Column(name = "color_favorito")
    String favoriteColor;

    /** Rol asociado al usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permiso", referencedColumnName = "rol_id")
    Rol permise;

    /** Imagen de perfil del usuario. */
    @Lob
    @Column(name = "imagePerfil", columnDefinition="LONGBLOB")
    private byte[] imagePerfil;

    /** Lista de usuarios seguidos por este usuario. */
    @OneToMany(fetch =  FetchType.LAZY)
    List<Follows> seguidos;

    /**
     * Método que devuelve los roles del usuario. En este caso, siempre devuelve el rol "USER".
     * @return Lista de roles del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    /**
     * Método que indica si la cuenta del usuario ha expirado (siempre devuelve true).
     * @return Si la cuenta no ha expirado.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Método que indica si la cuenta del usuario está bloqueada (siempre devuelve true).
     * @return Si la cuenta no está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Método que indica si las credenciales del usuario han expirado (siempre devuelve true).
     * @return Si las credenciales no han expirado.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * Método que indica si el usuario está habilitado (siempre devuelve true).
     * @return Si el usuario está habilitado.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}