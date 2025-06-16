package com.example.KAV.models.membresia;

import com.example.KAV.models.miembro.Miembro;
import com.example.KAV.models.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "membresias_usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembresiaMiembro {
    @EmbeddedId
    private MembresiaMiembroId id;
    @ManyToOne
    @MapsId("membresiaId")
    @JoinColumn(name = "membresia_id")
    private Membresia membresia;
    @ManyToOne
    @MapsId("miembroId")
    @JoinColumn(name = "miembro_id")
    private Miembro miembro;
    private Boolean activo = true;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
