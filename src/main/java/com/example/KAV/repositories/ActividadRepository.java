package com.example.KAV.repositories;

import com.example.KAV.models.actividad.Actividad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    Page<Actividad> findByActivoTrue(Pageable pageable);
}
