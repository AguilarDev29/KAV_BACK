package com.example.KAV.models.horario;

import com.example.KAV.models.actividad.Actividad;
import com.example.KAV.utils.enums.Dia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "horarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Horario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Dia dia;
    private LocalTime hora;
    @ManyToMany(mappedBy = "horarios", fetch = FetchType.LAZY)
    private Set<Actividad> actividades = new HashSet<>();
}