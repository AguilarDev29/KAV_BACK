package com.example.KAV.models.horario;

import com.example.KAV.models.Base;
import com.example.KAV.models.actividad.Actividad;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Horario extends Base {
    @Enumerated(EnumType.STRING)
    private Dia dia;
    private LocalTime hora;
    @ManyToMany(mappedBy = "horarios")
    private List<Actividad> actividades;
    enum Dia {
        LUNES,
        MARTES,
        MIERCOLES,
        JUEVES,
        VIERNES,
        SABADO,
        DOMINGO
    }
}
