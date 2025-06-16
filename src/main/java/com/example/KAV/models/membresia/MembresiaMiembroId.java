package com.example.KAV.models.membresia;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"membresiaId", "miembroId"})
@Embeddable
public class MembresiaMiembroId implements Serializable {
    @Column(name = "membresia_id")
    private Long membresiaId;
    @Column(name = "miembro_id")
    private Long miembroId;
}
