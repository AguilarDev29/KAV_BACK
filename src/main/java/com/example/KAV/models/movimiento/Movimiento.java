package com.example.KAV.models.movimiento;
import com.example.KAV.utils.enums.TipoIngreso;
import com.example.KAV.models.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Movimiento{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime horario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoIngreso tipo;

    @PrePersist
    protected void onCreate(){
        this.horario = LocalDateTime.now();
    }
}