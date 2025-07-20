package com.example.KAV.models.usuario.dto;
import com.example.KAV.models.enums.Sexo;

import java.time.LocalDate;

public record DtoDatosUsuarioSocio(
        String apellido,
        String nombre,
        String email,
        String telefono,
        LocalDate fechaNacimiento,
        Sexo genero,
        String direccion
) {
}
