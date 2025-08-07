package com.example.KAV.controllers;

import com.example.KAV.utils.enums.TipoPublicacion;
import com.example.KAV.models.publicacion.Publicacion;
import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.services.publicacion.IPublicacionService;
import com.example.KAV.services.publicacion.dto.DtoDatosPublicacion;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/v1/publicaciones")
public class PublicacionController {

    private final IPublicacionService publicacionService;

    public PublicacionController(IPublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping
    public ResponseEntity<Page<DtoDatosPublicacion>> getPublicaciones(Pageable pageable){
        return ResponseEntity.ok(publicacionService.getAll(pageable).map(DtoDatosPublicacion::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoDatosPublicacion> getPublicacion(@PathVariable Long id){
        var publicacion = publicacionService.getPublicacion(id);
        return ResponseEntity.ok(new DtoDatosPublicacion(publicacion));
    }

    @PostMapping("/create/noticia")
    public ResponseEntity<Publicacion> createNoticia(Usuario usuario,
                                                     @RequestBody @Valid DtoDatosPublicacion publicacion,
                                                     @RequestParam MultipartFile imagen) throws IOException {

        var createdNoticia = publicacionService
                .createPublicacion(usuario, new Publicacion(publicacion),imagen, TipoPublicacion.NOTICIA);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdNoticia.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdNoticia);
    }

    @PostMapping("/create/comunicado")
    public ResponseEntity<Publicacion> createComunicado(Usuario usuario,
                                                        @RequestBody @Valid DtoDatosPublicacion publicacion,
                                                        @RequestParam MultipartFile imagen) throws IOException {
        var createdComunicado = publicacionService
                .createPublicacion(usuario, new Publicacion(publicacion), imagen, TipoPublicacion.COMUNICADO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdComunicado.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdComunicado);
    }

    @PutMapping
    public ResponseEntity<DtoDatosPublicacion> updatePublicacion(@RequestBody @Valid DtoDatosPublicacion publicacion,
                                                                 @RequestParam MultipartFile imagen,
                                                                 @PathVariable Long id) throws IOException {
        var publicacionToUpdate = publicacionService.updatePublicacion(new Publicacion(publicacion),imagen, id);
        return ResponseEntity.ok(new DtoDatosPublicacion(publicacionToUpdate));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePublicacion(@PathVariable Long id){
        publicacionService.deletePublicacion(id);
        return ResponseEntity.noContent().build();
    }
}
