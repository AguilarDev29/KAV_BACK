package com.example.KAV.models.movimiento;

import com.example.KAV.models.Base;
import com.example.KAV.models.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "movimientos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Movimiento extends Base {

    private LocalDateTime ingreso;
    private LocalDateTime egreso;
    @ManyToMany
    @JoinTable(
            name = "movimientos_usuarios",
            joinColumns = @JoinColumn(name = "movimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;
}
