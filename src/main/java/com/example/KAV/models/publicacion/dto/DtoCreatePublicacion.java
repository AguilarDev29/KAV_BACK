package com.example.KAV.models.publicacion.dto;

import jakarta.validation.constraints.NotBlank;

public record DtoCreatePublicacion(
        @NotBlank(message = "El titulo es requerido")
        String titulo,
        @NotBlank(message = "La descripcion es requerida")
        String descripcion
) {
}
