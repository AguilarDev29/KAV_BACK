package com.example.KAV.models.provincia;

import com.example.KAV.models.Base;
import com.example.KAV.models.localidad.Localidad;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Provincia extends Base {

    private String nombre;
    @OneToMany(mappedBy = "provincia")
    private List<Localidad> localidades;
}
