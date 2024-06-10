package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.PublicationTweetDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import com.TFGGroupie.TFGGroupie.persistence.repository.PublicationTweetI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Implementación de la interfaz PublicationTweetServiceI que gestiona las operaciones
 * relacionadas con las publicaciones de tweets.
 */
@Service
public class PublicationTweetImpl implements PublicationTweetServiceI{

    private PublicationTweetI publicationRepository;

    @Autowired
    private JWTserviceImpl jwtService;
    /**
     * Constructor de la clase PublicationServiceImpl.
     *
     * @param publicationRepository Repositorio de publicaciones.
     */
    @Autowired
    public PublicationTweetImpl(PublicationTweetI publicationRepository) {
        this.publicationRepository = publicationRepository;
    }
    /**
     * Obtiene todas las publicaciones de tweets de un usuario a partir de su token JWT.
     *
     * @param jwt Token JWT del usuario.
     * @return Lista de publicaciones de tweets del usuario.
     */
    @Override
    public List<PublicationTweetDTO> getPublicationsByUserToken(String jwt) {

        Map<String, String> userIdAndUsername = jwtService.getId(jwt);
        String userId = userIdAndUsername.get("userId");

        List<PublicationTweet> publications = publicationRepository.findByAuthor(userId);
        List<PublicationTweetDTO> publicationDTOs = publications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        System.out.println("Publications by User por Token: " + publicationDTOs);
        return publicationDTOs;
    }
    /**
     * Obtiene todas las publicaciones de tweets de un usuario a partir de su identificador.
     *
     * @param id Identificador del usuario.
     * @return Lista de publicaciones de tweets del usuario.
     */
    @Override
    public List<PublicationTweetDTO> getPublicationsByUserID(String id) {

        List<PublicationTweet> publications = publicationRepository.findByAuthor(id);
        List<PublicationTweetDTO> publicationDTOs = publications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        System.out.println("Publications by User por ID: " + publicationDTOs);

        return publicationDTOs;

    }
    /**
     * Permite a un usuario publicar un nuevo tweet.
     *
     * @param token  Token JWT del usuario.
     * @param texto  Contenido del tweet.
     * @param publcico Tipo de visibilidad del tweet.
     * @return La publicación de tweet creada.
     */
    @Override
    public PublicationTweet publicarTweet(String token, String texto, String publcico) {

        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        String username = userIdAndUsername.get("username");

        PublicationTweet nuevoTweet = new PublicationTweet();
        nuevoTweet.setAuthor(userId);
        nuevoTweet.setText(texto);
        nuevoTweet.setNombreUsuario(username);
        nuevoTweet.setCreationDate(LocalDateTime.now());
        nuevoTweet.setEditionDate(LocalDateTime.now());
        nuevoTweet.setPublico(publcico);

        publicationRepository.save(nuevoTweet);
        return nuevoTweet;

    }
    /**
     * Convierte una entidad de PublicationTweet a PublicationTweetDTO.
     *
     * @param publicationEntity Entidad de la publicación de tweet.
     * @return DTO de la publicación de tweet.
     */
    private PublicationTweetDTO convertToDTO(PublicationTweet publicationEntity) {
        PublicationTweetDTO publicationDTO = new PublicationTweetDTO();
        publicationDTO.setPublicationId(publicationEntity.getPublicationId());
        publicationDTO.setAuthorId(publicationEntity.getAuthor());
        publicationDTO.setText(publicationEntity.getText());
        publicationDTO.setUsername(publicationEntity.getNombreUsuario());
        publicationDTO.setCreationDate(publicationEntity.getCreationDate());
        publicationDTO.setEditionDate(publicationEntity.getEditionDate());
        publicationDTO.setPublico(publicationEntity.getPublico());
        return publicationDTO;
    }
}
