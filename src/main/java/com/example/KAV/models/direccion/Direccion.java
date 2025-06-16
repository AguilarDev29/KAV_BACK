package com.example.KAV.models.direccion;

import com.example.KAV.models.Base;
import com.example.KAV.models.localidad.Localidad;
import com.example.KAV.models.miembro.Miembro;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Direccion extends Base {

    private String calle;
    private int numero;
    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Localidad localidad;
    @OneToMany(mappedBy = "direccion")
    private List<Miembro> miembros;
}
