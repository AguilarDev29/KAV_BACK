package com.example.KAV.services.publicacion;

import com.example.KAV.utils.enums.TipoPublicacion;
import com.example.KAV.models.publicacion.Publicacion;
import com.example.KAV.models.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IPublicacionService {

    Page<Publicacion> getAll(Pageable pageable);

    Publicacion getPublicacion(Long id);

    Publicacion createPublicacion(Usuario usuario, Publicacion publicacion, MultipartFile imagen,
                                  TipoPublicacion tipo) throws IOException;

    Publicacion updatePublicacion(Publicacion publicacion,MultipartFile imagen, Long id) throws IOException;

    void deletePublicacion(Long id);
}
