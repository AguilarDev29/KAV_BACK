package com.example.KAV.models.direccion.dto;

import com.example.KAV.models.direccion.Direccion;

public record DtoDireccion(
        String direccion
) {
    public DtoDireccion(Direccion direccion) {
        this(direccion.getCalle() + direccion.getNumero());
    }
}
