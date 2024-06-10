package com.repaso.repaso.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
/**
 * Entidad que representa un rol en la base de datos.
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    Long rolId;
    /** Nombre del rol. */
    @Column(name = "rol_nombre")
    String rolName;
    /**
     * Relación uno a muchos con los usuarios que tienen este rol.
     * Un rol puede tener varios usuarios.
     */
    @OneToMany(mappedBy = "permise", cascade = CascadeType.ALL)
    List<User> users;
}
