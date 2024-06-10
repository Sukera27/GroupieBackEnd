package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.CommentImgDTO;
import com.TFGGroupie.TFGGroupie.dto.CommentTweetDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImg;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImgComment;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweetComment;
import com.TFGGroupie.TFGGroupie.persistence.repository.CommentImgI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Implementación de la interfaz PublicationImgCommentServiceI para gestionar comentarios en publicaciones de imágenes.
 */
@Service
public class PublicationImgCommentImpl implements PublicationImgCommentServiceI {

    @Autowired
    private JWTserviceImpl jwtService;
    private CommentImgI commentRepository;

    @Autowired
    public PublicationImgCommentImpl(CommentImgI commentRepository) {
        this.commentRepository = commentRepository;
    }
    /**
     * Obtiene una lista de comentarios de una publicación de imagen por su ID.
     *
     * @param id ID de la publicación de imagen.
     * @return Lista de comentarios de la publicación de imagen.
     */
    @Override
    public List<CommentImgDTO> getCommentsByPublicationId(Long id) {
        PublicationImg publicacion = new PublicationImg();
        publicacion.setId(id);
        List<PublicationImgComment> comments = commentRepository.findByPublicationImg(publicacion);

        // Convert each PublicationImgComment to CommentImgDTO using fromEntity method
        List<CommentImgDTO> commentDTOs = comments.stream()
                .map(comment -> CommentImgDTO.fromEntity(comment))
                .collect(Collectors.toList());

        return commentDTOs;
    }

    /**
     * Publica un nuevo comentario en una publicación de imagen.
     *
     * @param token Token JWT del usuario que realiza el comentario.
     * @param texto Texto del comentario.
     * @param id    ID de la publicación de imagen.
     * @return El comentario publicado.
     */
    @Override
    public PublicationImgComment publicarComentario(String token, String texto, Long id) {


        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        String username = userIdAndUsername.get("username");

        PublicationImgComment nuevoComentario = new PublicationImgComment();

        PublicationImg publicacioLikeada = new PublicationImg();
        publicacioLikeada.setId(id);
        nuevoComentario.setPublicationImg(publicacioLikeada);
        nuevoComentario.setNombreUsuario(texto);
        nuevoComentario.setAuthor(username);

        commentRepository.save(nuevoComentario);
        return nuevoComentario;
    }
}
