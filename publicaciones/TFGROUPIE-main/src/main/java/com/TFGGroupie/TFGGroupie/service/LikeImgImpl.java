package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.LikeImgDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.ImgLike;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImg;
import com.TFGGroupie.TFGGroupie.persistence.model.TweetLikes;
import com.TFGGroupie.TFGGroupie.persistence.repository.LikeImgI;
import com.TFGGroupie.TFGGroupie.persistence.repository.LikeTweetI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
 * Implementación del servicio LikeImgServiceI para manejar likes en publicaciones de imágenes.
 */
@Service
public class LikeImgImpl implements LikeImgServiceI{

    private final LikeImgI likeRepo;
    private final JWTserviceImpl jwtService;

    @Autowired
    public LikeImgImpl(LikeImgI likeRepo, JWTserviceImpl jwtService) {
        this.likeRepo = likeRepo;
        this.jwtService = jwtService;
    }
    /**
     * Da like o quita el like de una publicación de imagen.
     *
     * @param jwt          Token JWT del usuario que da o quita el like.
     * @param idPublication ID de la publicación de imagen.
     * @return true si se dio like, false si se quitó el like.
     */
    @Override
    public Boolean likePublication(String jwt, Long idPublication) {
        Map<String, String> userIdAndUsername = jwtService.getId(jwt);
        String userId = userIdAndUsername.get("userId");

        PublicationImg publicationImg = new PublicationImg();
        publicationImg.setId(idPublication);

        // Buscar y eliminar el like por el id del usuario y de la publicación
        ImgLike existingLike = likeRepo.findByAuthorAndPublicationImg(userId, publicationImg);

        if (existingLike != null) {
            likeRepo.delete(existingLike);
            return false;
        }

        // Crear un nuevo like
        ImgLike like = new ImgLike();

        like.setAuthor(userId);
        like.setPublicationImg(publicationImg);
        likeRepo.save(like);

        return true;
    }
    /**
     * Verifica si un usuario ha dado like a una publicación de imagen.
     *
     * @param jwt          Token JWT del usuario.
     * @param idPublication ID de la publicación de imagen.
     * @return DTO que representa la información del like.
     */
    public LikeImgDTO existeLike(String jwt, Long idPublication) {
        Map<String, String> userIdAndUsername = jwtService.getId(jwt);
        String userId = userIdAndUsername.get("userId");

        PublicationImg publicationImg = new PublicationImg();
        publicationImg.setId(idPublication);

        ImgLike like = likeRepo.findByAuthorAndPublicationImg(userId, publicationImg);

        // Transformar ImgLike a LikeImgDTO usando fromEntity
        return LikeImgDTO.fromEntity(like);
    }
    /**
     * Obtiene la cantidad de likes de una publicación de imagen.
     *
     * @param id ID de la publicación de imagen.
     * @return Número de likes de la publicación.
     */
    @Override
    public int findLikesByPublication(Long id) {

        return likeRepo.findLikesByPublication(id);
    }
}
