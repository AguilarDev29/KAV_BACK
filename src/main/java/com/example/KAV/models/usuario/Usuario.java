package com.example.KAV.models.usuario;
import com.example.KAV.models.actividad.Actividad;
import com.example.KAV.models.direccion.Direccion;
import com.example.KAV.models.enums.Rol;
import com.example.KAV.models.enums.Sexo;
import com.example.KAV.models.movimiento.Movimiento;
import com.example.KAV.models.publicacion.Publicacion;
import com.example.KAV.models.usuario.dto.DtoAltaUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Rol rol;
    @NotBlank
    private String apellido;
    @NotBlank
    private String nombre;
    @NotBlank
    private String dni;
    @Email
    private String email;
    private String telefono;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Sexo sexo;
    @NotNull
    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    private String foto;
    private String pin;
    private Boolean activo;
    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    private Set<Actividad> actividades = new HashSet<>();
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Publicacion> publicaciones = new HashSet<>();
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Movimiento> movimientos = new HashSet<>();
    private LocalDateTime ultimoIngreso;
    private LocalDateTime ultimoEgreso;

    public Usuario(DtoAltaUsuario usuario) {
        this.apellido = usuario.apellido();
        this.nombre = usuario.nombre();
        this.dni = usuario.dni();
        this.sexo = usuario.sexo();
        this.email = usuario.email();
        this.telefono = usuario.telefono();
        this.rol = Rol.valueOf(usuario.rol());
    }

    @PrePersist
    protected void onCreate(){
        this.activo = true;
        this.rol = Rol.USER;
    }

}
