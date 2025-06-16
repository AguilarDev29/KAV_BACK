package com.example.KAV.models.membresia;

import com.example.KAV.models.Base;
import com.example.KAV.models.miembro.Miembro;
import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.models.actividad.Actividad;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "membresias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Membresia extends Base {
    private String nombre;
    @OneToMany(mappedBy = "membresia")
    private List<MembresiaMiembro> membresiasMiembros = new ArrayList<>();
    @OneToMany(mappedBy = "membresia")
    private List<Actividad> actividades = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Membresia)) return false;
        return id != null && id.equals(((Membresia) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
