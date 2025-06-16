package com.example.KAV.models.miembro;

import com.example.KAV.models.Base;
import com.example.KAV.models.membresia.Membresia;
import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.models.actividad.Actividad;
import com.example.KAV.models.direccion.Direccion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "miembros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Miembro extends Base {

    private String apellido;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String foto;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membresia_id")
    private Membresia membresia;
    private Boolean esInstructor = false;
    @ManyToMany(mappedBy = "instructores")
    private List<Actividad> actividadACargo;
    enum Sexo {
        MASCULINO, FEMENINO
    }
}
