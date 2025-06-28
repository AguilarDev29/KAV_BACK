package com.example.KAV.models.usuario;

import com.example.KAV.models.Base;
import com.example.KAV.models.actividad.Actividad;
import com.example.KAV.models.miembro.Miembro;
import com.example.KAV.models.movimiento.Movimiento;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Base {

    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USER;
    private String pin;
    private Boolean activo = true;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Miembro miembro;
    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    private List<Actividad> actividades = new ArrayList<>();
    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    private List<Movimiento> movimientos = new ArrayList<>();
    private LocalDateTime ultimoIngreso;
    private LocalDateTime ultimoEgreso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        return id != null && id.equals(((Usuario) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    enum Rol {
        ADMIN,
        USER,
        STAFF,
        DEV
    }
}
