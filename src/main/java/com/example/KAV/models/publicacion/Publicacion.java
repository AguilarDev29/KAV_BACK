package com.example.KAV.models.publicacion;
import com.example.KAV.models.enums.TipoPublicacion;
import com.example.KAV.models.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "publicaciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
    private String imagen;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_publicacion")
    private TipoPublicacion tipoPublicacion;

}
