package com.example.KAV.repositories;

import com.example.KAV.models.publicacion.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Long, Publicacion> {
}
