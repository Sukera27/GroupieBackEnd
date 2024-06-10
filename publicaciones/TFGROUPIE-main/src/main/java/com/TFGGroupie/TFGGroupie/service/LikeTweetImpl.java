package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.LikeDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import com.TFGGroupie.TFGGroupie.persistence.model.TweetLikes;
import com.TFGGroupie.TFGGroupie.persistence.repository.LikeTweetI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
 * Implementación del servicio para gestionar likes en publicaciones de tweets.
 */
@Service
public class LikeTweetImpl implements LikeTweetServiceI {

    private final LikeTweetI likeRepo;
    private final JWTserviceImpl jwtService;

    @Autowired
    public LikeTweetImpl(LikeTweetI likeRepo, JWTserviceImpl jwtService) {
        this.likeRepo = likeRepo;
        this.jwtService = jwtService;
    }
    /**
     * Da like o quita el like de una publicación de tweet.
     *
     * @param jwt          Token JWT del usuario que da o quita el like.
     * @param idPublication ID de la publicación de tweet.
     * @return true si se dio like, false si se quitó el like.
     */
    @Override
    public Boolean likePublication(String jwt, Long idPublication) {
        Map<String, String> userIdAndUsername = jwtService.getId(jwt);
        String userId = userIdAndUsername.get("userId");

        // Buscar y eliminar el like por el id del usuario y de la publicación
        PublicationTweet publicacion = new PublicationTweet();
        publicacion.setPublicationId(idPublication);
        TweetLikes existingLike = likeRepo.findByAuthorAndPublicationID(userId, publicacion);

        if (existingLike != null) {
            likeRepo.delete(existingLike);
            return false;
        }

        // Crear un nuevo like
        TweetLikes like = new TweetLikes();
        like.setAuthor(userId);
        PublicationTweet publicacion2 = new PublicationTweet();
        publicacion2.setPublicationId(idPublication);
        like.setPublicationID(publicacion2);
        likeRepo.save(like);

        return true;
    }
    /**
     * Verifica si un usuario ha dado like a una publicación de tweet.
     *
     * @param jwt          Token JWT del usuario.
     * @param idPublication ID de la publicación de tweet.
     * @return DTO que representa la información del like.
     */
    @Override
    public LikeDTO existeLike(String jwt, Long idPublication) {
        Map<String, String> userIdAndUsername = jwtService.getId(jwt);
        String userId = userIdAndUsername.get("userId");
        PublicationTweet publicacion = new PublicationTweet();
        publicacion.setPublicationId(idPublication);
        TweetLikes like = likeRepo.findByAuthorAndPublicationID(userId,publicacion);
        return LikeDTO.fromEntity(like);
    }
    /**
     * Obtiene la cantidad de likes de una publicación de tweet.
     *
     * @param id ID de la publicación de tweet.
     * @return Número de likes de la publicación.
     */
    @Override
    public int findLikesByPublication(Long id) {

        return likeRepo.findLikesByPublication(id);
    }
}
