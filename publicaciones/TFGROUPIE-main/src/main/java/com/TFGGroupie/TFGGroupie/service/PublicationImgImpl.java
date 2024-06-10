package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.PublicationImgDTO;
import com.TFGGroupie.TFGGroupie.dto.PublicationTweetDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImg;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationTweet;
import com.TFGGroupie.TFGGroupie.persistence.repository.PublicationImgI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Implementación de la interfaz PublicationImgServiceI que gestiona las operaciones relacionadas con publicaciones de imágenes.
 */
@Service
public class PublicationImgImpl implements PublicationImgServiceI {

    @Autowired
    private PublicationImgI imageRepository;
    @Autowired
    private JWTserviceImpl jwtService;
    /**
     * Publica una nueva imagen.
     *
     * @param file    Archivo de la imagen.
     * @param token   Token JWT del usuario que publica la imagen.
     * @param publcico Indicador de si la imagen es pública o no.
     * @return La imagen publicada.
     * @throws IOException Si ocurre un error al leer el archivo de la imagen.
     */
    public PublicationImg publicarImagen(MultipartFile file, String token,String publcico) throws IOException {

        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");
        String username = userIdAndUsername.get("username");

        PublicationImg image = new PublicationImg();

        image.setAuthor(userId);
        image.setName(username);
        image.setData(file.getBytes());
        image.setCreationDate(LocalDateTime.now());
        image.setEditionDate(LocalDateTime.now());
        image.setPublico(publcico);
        return imageRepository.save(image);

    }
    /**
     * Obtiene todas las imágenes pertenecientes a un usuario específico.
     *
     * @param token Token JWT del usuario cuyas imágenes se desean obtener.
     * @return Lista de imágenes del usuario.
     */
    public List<PublicationImgDTO> obtenerMisImagenes(String token) {
        Map<String, String> userIdAndUsername = jwtService.getId(token);
        String userId = userIdAndUsername.get("userId");

        List<PublicationImg> images = imageRepository.findByAuthor(userId);

        // Convert each PublicationImg to PublicationImgDTO using fromEntity method
        List<PublicationImgDTO> imageDTOs = images.stream()
                .map(PublicationImgDTO::fromEntity)
                .collect(Collectors.toList());

        return imageDTOs;
    }
    /**
     * Obtiene todas las publicaciones de imágenes de un usuario específico.
     *
     * @param id ID del usuario cuyas publicaciones de imágenes se desean obtener.
     * @return Lista de publicaciones de imágenes del usuario.
     */
    @Override
    public List<PublicationImgDTO> getPublicationsImgByUserID(String id) {

        List<PublicationImg> publications = imageRepository.findByAuthor(id);
        // Convert each PublicationImg to PublicationImgDTO using fromEntity method
        List<PublicationImgDTO> publicationsDTOs = publications.stream()
                .map(PublicationImgDTO::fromEntity)
                .collect(Collectors.toList());

        return publicationsDTOs;


    }

}
