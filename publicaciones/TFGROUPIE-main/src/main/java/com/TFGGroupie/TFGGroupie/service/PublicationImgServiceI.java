package com.TFGGroupie.TFGGroupie.service;

import com.TFGGroupie.TFGGroupie.dto.PublicationImgDTO;
import com.TFGGroupie.TFGGroupie.dto.PublicationTweetDTO;
import com.TFGGroupie.TFGGroupie.persistence.model.PublicationImg;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
/**
 * Interfaz para definir operaciones relacionadas con publicaciones de imágenes.
 */
public interface PublicationImgServiceI {
    /**
     * Publica una nueva imagen.
     *
     * @param file     Archivo de la imagen a publicar.
     * @param token    Token de autenticación del usuario que publica la imagen.
     * @param publcico Indicador de si la imagen es pública o no.
     * @return La publicación de la imagen creada.
     * @throws IOException Si ocurre un error al leer el archivo de la imagen.
     */
    public PublicationImg publicarImagen(MultipartFile file, String token,String publcico) throws IOException;

    /**
     * Obtiene todas las imágenes publicadas por el usuario asociado al token de autenticación proporcionado.
     *
     * @param token Token de autenticación del usuario cuyas imágenes se desean obtener.
     * @return Lista de DTO (Data Transfer Object) de publicaciones de imágenes del usuario.
     */
    public List<PublicationImgDTO> obtenerMisImagenes(String token);
    /**
     * Obtiene todas las publicaciones de imágenes asociadas al ID de usuario especificado.
     *
     * @param id ID del usuario cuyas publicaciones de imágenes se desean obtener.
     * @return Lista de DTO de publicaciones de imágenes del usuario.
     */
    List<PublicationImgDTO> getPublicationsImgByUserID(String id);



}
