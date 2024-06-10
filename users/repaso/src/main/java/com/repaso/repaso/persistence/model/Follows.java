/*package com.repaso.repaso.persistence.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="Follows")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Follows implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "usuario_id")
    @JsonIgnoreProperties("seguidos") // Evitar serialización recursiva con User
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "usuario_id")
    @JsonIgnoreProperties("seguidos") // Evitar serialización recursiva con User
    private User followed;

}

*/



































package com.repaso.repaso.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
/**
 * Representa la entidad Follows que define la relación entre usuarios,
 * donde un usuario sigue a otro.
 */
@Entity
@Table(name="Follows")
@Data
@NoArgsConstructor
public class Follows implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;
    /**
     * Usuario que realiza el seguimiento a otro usuario.
     */
    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "usuario_id")
    private User follower;
    /**
     * Usuario que es seguido por otro usuario.
     */
    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "usuario_id")
    private User followed;

}

