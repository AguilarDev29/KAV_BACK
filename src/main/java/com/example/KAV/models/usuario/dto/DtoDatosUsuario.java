package com.example.KAV.models.usuario.dto;

import com.example.KAV.utils.enums.Rol;
import com.example.KAV.models.usuario.Usuario;

public record DtoDatosUsuario(
        String apellido,
        String nombre,
        String email,
        Rol rol,
        String dni,
        String estado
) {
    public DtoDatosUsuario(Usuario usuario) {
        this(usuario.getApellido(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getDni(),
                usuario.getActivo() ? "Activo" : "Inactivo");
    }
}
