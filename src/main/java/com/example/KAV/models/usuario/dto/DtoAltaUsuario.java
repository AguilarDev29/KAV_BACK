package com.example.KAV.models.usuario.dto;

import com.example.KAV.models.enums.Sexo;

public record DtoAltaUsuario(
        String apellido,
        String nombre,
        String dni,
        Sexo sexo,
        String email,
        String telefono,
        String rol
) {
}
