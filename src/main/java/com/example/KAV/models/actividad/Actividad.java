package com.example.KAV.models.actividad;
import com.example.KAV.models.horario.Horario;
import com.example.KAV.models.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actividades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Actividad{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "actividades_instructores",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private Set<Usuario> instructores = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "actividades_usuarios",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "actividad_horario",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = @JoinColumn(name = "horario_id")
    )
    private Set<Horario> horarios = new HashSet<>();
    private Double duracion;
    private Boolean activo;

    @PrePersist
    protected void onCreate(){
        this.activo = true;
    }

}