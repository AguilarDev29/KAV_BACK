package com.example.KAV.models.localidad.dto;

import com.example.KAV.models.localidad.Localidad;

public record DtoLocalidad(String nombre) {

    public DtoLocalidad(Localidad localidad){
        this(localidad.getNombre());
    }
}
