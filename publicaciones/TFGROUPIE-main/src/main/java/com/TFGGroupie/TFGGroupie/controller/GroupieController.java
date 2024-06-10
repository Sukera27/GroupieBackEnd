package com.TFGGroupie.TFGGroupie.controller;

import com.TFGGroupie.TFGGroupie.dto.*;
import com.TFGGroupie.TFGGroupie.persistence.model.*;
import com.TFGGroupie.TFGGroupie.persistence.repository.PublicationImgI;
import com.TFGGroupie.TFGGroupie.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.stream.Collectors;
/**
 * Controlador para manejar las operaciones relacionadas con las publicaciones y comentarios.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class GroupieController {

    private PublicationTweetServiceI publicationService;

    private PublicationImgCommentServiceI commetImg;
    private final PublicationTweetCommentServiceI publicationService2;

    private final LikeTweetServiceI likeService;

    private final LikeImgServiceI likeServiceimg;


    private final PublicationImgServiceI pubicarImgService;





    @Autowired
    public GroupieController(PublicationTweetServiceI publicationService,
                             PublicationTweetCommentServiceI publicationService2,LikeTweetServiceI likeService,
                             PublicationImgServiceI pubicarImgService, LikeImgServiceI likeServiceimg,PublicationImgCommentServiceI commetImg){
        this.publicationService = publicationService;
        this.publicationService2 = publicationService2;
        this.likeService = likeService;
        this.pubicarImgService = pubicarImgService;
        this.likeServiceimg = likeServiceimg;
        this.commetImg = commetImg;

    }
    /**
     * Obtiene todas las publicaciones de un usuario utilizando el token de autorización.
     *
     * @param token Token de autorización del usuario.
     * @return Lista de publicaciones del usuario.
     */
    @GetMapping("/user/publications")
    public List<PublicationTweetDTO> getPublicationsByUserId(@RequestHeader("Authorization") String token) {

        // Luego puedes llamar al servicio para obtener las publicaciones del usuario
        return publicationService.getPublicationsByUserToken(token);

    }
    /**
     * Obtiene todas las publicaciones de un usuario por su ID.
     *
     * @param userId ID del usuario.
     * @return Lista de publicaciones del usuario.
     */
    @GetMapping("/{userId}/publications")
    public List<PublicationTweetDTO> getPublicationsByUserId2(@PathVariable String userId) {

        return publicationService.getPublicationsByUserID(userId);

    }
    /**
     * Obtiene todos los comentarios de una publicación de tweet por su ID.
     *
     * @param publicationId ID de la publicación de tweet.
     * @return Lista de comentarios de la publicación de tweet.
     */
    @GetMapping("/{publicationId}/comments")
    public ResponseEntity<List<CommentTweetDTO>> getCommentsByPublicationId(@PathVariable Long publicationId) {
        List<CommentTweetDTO> comments = publicationService2.getCommentsByPublicationId(publicationId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    /*No pasa nada porque cuando le de like el boton va a cambier y va a hacer que la funcion haga la de borrar like*/
    /**
     * Endpoint para dar like a una publicación de tweet.
     *
     * @param token       Token de autorización del usuario.
     * @param publication ID de la publicación a la que se da like.
     * @return `true` si se dio like exitosamente, `false` si se quitó el like.
     */
    @PostMapping("/like/{publication}")
    public ResponseEntity<Boolean> Like(@RequestHeader("Authorization") String token,@PathVariable  Long publication) {

        Boolean like = likeService.likePublication(token,publication);
        return new ResponseEntity<>(like, HttpStatus.OK);

    }
    /**
     * Endpoint para publicar un nuevo tweet.
     *
     * @param token   Token de autorización del usuario.
     * @param texto   Texto del tweet.
     * @param publico Nivel de privacidad del tweet.
     * @return La publicación de tweet creada.
     */
    @PostMapping("/newTweet")
    public ResponseEntity<PublicationTweet> NewTweet (@RequestHeader("Authorization") String token,@RequestHeader("texto") String texto, @RequestHeader("publicacion") String publico) {

        PublicationTweet publicacion = publicationService.publicarTweet(token,texto,publico);
        return new ResponseEntity<>(publicacion, HttpStatus.OK);

    }
    /**
     * Endpoint para publicar un nuevo comentario en una publicación de tweet.
     *
     * @param token Token de autorización del usuario.
     * @param texto Texto del comentario.
     * @param id    ID de la publicación de tweet donde se realiza el comentario.
     * @return El comentario creado.
     */
    @PostMapping("/newComment")
    public ResponseEntity<CommentTweetDTO> NewComment (@RequestHeader("Authorization") String token,@RequestHeader("texto") String texto, @RequestHeader("Id") Long id) {

        CommentTweetDTO comment = publicationService2.publicarComentario(token,texto,id);
        return new ResponseEntity<>(comment, HttpStatus.OK);

    }
    /**
     * Obtiene las publicaciones de tweet de varios usuarios.
     *
     * @param userIds Lista de IDs de usuarios.
     * @return Lista de publicaciones de tweet de los usuarios especificados.
     */
    @PostMapping("/publications/byUserIds")
    public List<PublicationTweetDTO> getPublicationsByUserIds(@RequestBody List<String> userIds) {
        return userIds.stream()
                .flatMap(id -> publicationService.getPublicationsByUserID(id).stream())
                .collect(Collectors.toList());
    }
    /**
     * Verifica si un usuario ha dado like a una publicación de tweet.
     *
     * @param token       Token de autorización del usuario.
     * @param publication ID de la publicación.
     * @return DTO con la información del like si existe.
     */
    @GetMapping("/likeExist/{publication}")
    public LikeDTO likeExist(@RequestHeader("Authorization") String token,@PathVariable  Long publication) {

        LikeDTO like = likeService.existeLike(token,publication);
        return like;
    }
    /**
     * Endpoint para subir una imagen.
     *
     * @param file    Archivo de la imagen.
     * @param token   Token de autorización del usuario.
     * @param publico Nivel de privacidad de la imagen.
     * @return Respuesta indicando el resultado de la operación.
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,@RequestHeader("Authorization") String token,@RequestHeader("publicacion") String publico) {
        try {
            pubicarImgService.publicarImagen(file,token,publico);
            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }
    /**
     * Obtiene las imágenes subidas por el usuario actual.
     *
     * @param token Token de autorización del usuario.
     * @return Lista de imágenes subidas por el usuario.
     */
    @GetMapping("/myImgs")
    public List<PublicationImgDTO> myImgs(@RequestHeader("Authorization") String token) {
        List<PublicationImgDTO> myImages = pubicarImgService.obtenerMisImagenes(token);
        return myImages;
    }
    /**
     * Endpoint para dar like a una publicación de imagen.
     *
     * @param token       Token de autorización del usuario.
     * @param publication ID de la publicación a la que se da like.
     * @return `true` si se dio like exitosamente, `false` si se quitó el like.
     */
    @PostMapping("/likeImg/{publication}")
    public ResponseEntity<Boolean> LikeImg(@RequestHeader("Authorization") String token,@PathVariable  Long publication) {

        Boolean like = likeServiceimg.likePublication(token,publication);
        return new ResponseEntity<>(like, HttpStatus.OK);

    }
    /**
     * Endpoint para publicar un nuevo comentario en una publicación de imagen.
     *
     * @param token Token de autorización del usuario.
     * @param texto Texto del comentario.
     * @param id    ID de la publicación de imagen donde se realiza el comentario.
     * @return El comentario creado.
     */
    @PostMapping("/newCommentImg")
    public ResponseEntity<PublicationImgComment> NewCommentImg (@RequestHeader("Authorization") String token, @RequestHeader("texto") String texto, @RequestHeader("Id") Long id) {

        PublicationImgComment comment = commetImg.publicarComentario(token,texto,id);
        return new ResponseEntity<>(comment, HttpStatus.OK);

    }
    /**
     * Verifica si un usuario ha dado like a una publicación de imagen.
     *
     * @param token       Token de autorización del usuario.
     * @param publication ID de la publicación.
     * @return DTO con la información del like si existe.
     */
    @GetMapping("/likeImgExist/{publication}")
    public LikeImgDTO likeImgExist(@RequestHeader("Authorization") String token,@PathVariable  Long publication) {

        LikeImgDTO like = likeServiceimg.existeLike(token,publication);
        return like;
    }
    /**
     * Obtiene todos los comentarios de una publicación de imagen por su ID.
     *
     * @param publicationId ID de la publicación de imagen.
     * @return Lista de comentarios de la publicación de imagen.
     */
    @GetMapping("/{publicationId}/commentsImg")
    public ResponseEntity<List<CommentImgDTO>> getCommentsImgByPublicationId(@PathVariable Long publicationId) {
        List<CommentImgDTO> comments = commetImg.getCommentsByPublicationId(publicationId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    /**
     * Obtiene las publicaciones de imágenes de varios usuarios.
     *
     * @param userIds Lista de IDs de usuarios.
     * @return Lista de publicaciones de imágenes de los usuarios especificados.
     */
    @PostMapping("/publicationsImg/byUserIds")
    public List<PublicationImgDTO> getPublicationsImgByUserIds(@RequestBody List<String> userIds) {
        return userIds.stream()
                .flatMap(id -> pubicarImgService.getPublicationsImgByUserID(id).stream())
                .collect(Collectors.toList());
    }
    /**
     * Obtiene las publicaciones de imágenes de un usuario por su ID.
     *
     * @param token Token de autorización del usuario.
     * @return Lista de publicaciones de imágenes del usuario.
     */
    @GetMapping("/user/publicationsIMG/{id}")
    public List<PublicationImgDTO> getPublicationsImgByUserId(@PathVariable("id") String token) {

        // Luego puedes llamar al servicio para obtener las publicaciones del usuario
        return pubicarImgService.getPublicationsImgByUserID(token);

    }
    /**
     * Obtiene la cantidad de likes de una publicación de tweet por su ID.
     *
     * @param idPublicacion ID de la publicación de tweet.
     * @return Cantidad de likes de la publicación.
     */

    @GetMapping("getLikes/{idPublicacion}")
    public int getLikesByPublicationId (@PathVariable("idPublicacion") Long idPublicacion) {

        return likeService.findLikesByPublication(idPublicacion);

    }
    /**
     * Obtiene la cantidad de likes de una publicación de imagen por su ID.
     *
     * @param idPublicacion ID de la publicación de imagen.
     * @return Cantidad de likes de la publicación.
     */
    @GetMapping("getLikesImg/{idPublicacion}")
    public int getLikesIMGByPublicationId (@PathVariable("idPublicacion") Long idPublicacion) {

        return likeServiceimg.findLikesByPublication(idPublicacion);

    }


}

