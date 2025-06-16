package com.example.KAV.models.localidad;

import com.example.KAV.models.Base;
import com.example.KAV.models.provincia.Provincia;
import com.example.KAV.models.direccion.Direccion;
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
public class Localidad extends Base {
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;
    @OneToMany(mappedBy = "localidad")
    private List<Direccion> direcciones;
}
