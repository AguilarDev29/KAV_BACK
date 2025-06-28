package com.example.KAV.models.actividad;

import com.example.KAV.models.*;
import com.example.KAV.models.horario.Horario;
import com.example.KAV.models.miembro.Miembro;
import com.example.KAV.models.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Actividad extends Base {

    private String nombre;
    private String descripcion;
    @ManyToMany
    @JoinTable(
            name = "actividades_instructores",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private List<Miembro> instructores;
    @ManyToMany
    @JoinTable(
            name = "actividades_usuarios",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;
    @ManyToMany
    @JoinTable(
            name = "actividad_horario",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = @JoinColumn(name = "horario_id")
    )
    private List<Horario> horarios;
}
