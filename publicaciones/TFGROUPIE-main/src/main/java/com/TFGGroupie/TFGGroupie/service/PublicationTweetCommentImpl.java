package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.CommentTweetDTO;
import com.TFGGroupie.TFGGroupie.dto.PublicationTweetDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweetComment;
import com.TFGGroupie.TFGGroupie.persistence.repository.CommentTweetI;
import com.TFGGroupie.TFGGroupie.persistence.repository.PublicationTweetI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Implementación del servicio para gestionar los comentarios de publicaciones de tweets.
 */
@Service
public class PublicationTweetCommentImpl implements PublicationTweetCommentServiceI{

    @Autowired
    private JWTserviceImpl jwtService;
    private CommentTweetI commentRepository;

    @Autowired
    public PublicationTweetCommentImpl(CommentTweetI commentRepository) {
        this.commentRepository = commentRepository;
    }
    /**
     * Obtiene todos los comentarios asociados a una publicación de tweet específica.
     *
     * @param id Identificador de la publicación de tweet.
     * @return Lista de comentarios de la publicación de tweet.
     */
    @Override
    public List<CommentTweetDTO> getCommentsByPublicationId(Long id) {
        PublicationTweet publicacion = new PublicationTweet();
        publicacion.setPublicationId(id);
        List<PublicationTweetComment> comments = commentRepository.findByPublicationID(publicacion);
        List<CommentTweetDTO> commentDTOs = comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        System.out.println("Comments by Publication ID: " + commentDTOs);

        return commentDTOs;
    }




    /**
     * Permite a un usuario publicar un nuevo comentario en una publicación de tweet.
     *
     * @param token Token JWT del usuario.
     * @param texto Contenido del comentario.
     * @param id    Identificador de la publicación de tweet.
     * @return DTO del comentario publicado.
     */
    @Override
    public CommentTweetDTO publicarComentario(String token, String texto, Long id) {


        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        String username = userIdAndUsername.get("username");

        PublicationTweetComment nuevoComentario = new PublicationTweetComment();

        PublicationTweet publicacion = new PublicationTweet();
        publicacion.setPublicationId(id);
        nuevoComentario.setPublicationID(publicacion);
        nuevoComentario.setNombreUsuario(texto);
        nuevoComentario.setAuthor(username);

        commentRepository.save(nuevoComentario);
        CommentTweetDTO commnet = convertToDTO(nuevoComentario);
        return commnet;
    }



    /**
     * Convierte un objeto PublicationTweetComment a CommentTweetDTO.
     *
     * @param commentEntity Objeto PublicationTweetComment a convertir.
     * @return DTO de comentario de tweet convertido.
     */
    private CommentTweetDTO convertToDTO(PublicationTweetComment commentEntity) {
        CommentTweetDTO commentDTO = new CommentTweetDTO();
        commentDTO.setCommentId(commentEntity.getCommentId());
        commentDTO.setPublicationID(commentEntity.getPublicationID().getPublicationId());
        commentDTO.setNombreUsuario(commentEntity.getNombreUsuario());
        commentDTO.setAuthor(commentEntity.getAuthor());
        return commentDTO;
    }


















































    /*
    @Override
    public List<CommentTweetDTO> getCommentsByPublicationId(Long id) {

        // Obtener la publicación por su id
        //PublicationTweet publication = new PublicationTweet();
        //publication.setPublicationId(id);

        List<PublicationTweetComment> publications = commentRepository.findByPublicationID(id);
        List<CommentTweetDTO> commentDTOs = publications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        System.out.println("Publications by User por ID: " + commentDTOs);

        return commentDTOs;

    }
*/
}
