package com.example.KAV.services.publicacion.dto;

import com.example.KAV.models.publicacion.Publicacion;

public record DtoDatosPublicacion(
        String titulo,
        String descripcion,
        String imagen
) {

    public DtoDatosPublicacion(Publicacion p) {
        this(p.getTitulo(), p.getDescripcion(), p.getImagen());
    }
}
