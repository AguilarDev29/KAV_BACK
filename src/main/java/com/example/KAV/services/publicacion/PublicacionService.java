package com.example.KAV.services.publicacion;

import com.example.KAV.utils.ISaveFiles;
import com.example.KAV.utils.enums.TipoPublicacion;
import com.example.KAV.models.publicacion.Publicacion;
import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.repositories.PublicacionRepository;
import com.example.KAV.utils.exceptions.PublicacionException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PublicacionService implements IPublicacionService, ISaveFiles {

    private final PublicacionRepository publicacionRepository;

    public PublicacionService(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    @Override
    public Page<Publicacion> getAll(Pageable pageable) {
        return publicacionRepository.findAll(pageable);
    }

    @Override
    public Publicacion getPublicacion(Long id) {
        return publicacionRepository.findById(id).orElseThrow(() ->
                        new PublicacionException(HttpStatus.NOT_FOUND, "Publicación no encontrada"));
    }

    @Override
    public Publicacion createPublicacion(Usuario usuario, Publicacion publicacion,
                                         MultipartFile imagen, TipoPublicacion tipo) throws IOException {

        if(imagen.isEmpty()) throw new RuntimeException("La imagen es requerida");
        else{
            String rutaImagen = saveImagen(imagen);
            publicacion.setImagen(rutaImagen);
        }

        if (tipo.name().equals(TipoPublicacion.NOTICIA.name()))
            publicacion.setTipoPublicacion(TipoPublicacion.NOTICIA);
        if (tipo.name().equals(TipoPublicacion.COMUNICADO.name()))
            publicacion.setTipoPublicacion(TipoPublicacion.COMUNICADO);
        publicacion.setUsuario(usuario);
        
        return publicacionRepository.save(publicacion);
    }

    @Override
    public Publicacion updatePublicacion(Publicacion publicacion, MultipartFile imagen, Long id) throws IOException {
        var publicacionToUpdate = publicacionRepository.findById(id);

        if(publicacionToUpdate.isEmpty())
            throw new PublicacionException(HttpStatus.NOT_FOUND,
                    "Error al actualizar publicacion");

        if(!imagen.isEmpty())
        {
            String rutaImagen = saveImagen(imagen);
            publicacionToUpdate.get().setImagen(rutaImagen);
        }
        if(publicacion.getTitulo() != null) publicacionToUpdate.get()
                .setTitulo(publicacion.getTitulo());
        if(publicacion.getDescripcion() != null) publicacionToUpdate.get()
                .setDescripcion(publicacion.getDescripcion());

        return publicacionRepository.save(publicacionToUpdate.get());
    }

    @Override
    public void deletePublicacion(Long id) {
        if(publicacionRepository.findById(id).isEmpty())
            throw new PublicacionException(HttpStatus.NOT_FOUND,
                "Error al eliminar la publicación");

        publicacionRepository.deleteById(id);
    }
    
}
