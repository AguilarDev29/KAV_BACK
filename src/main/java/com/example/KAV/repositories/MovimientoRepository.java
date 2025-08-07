package com.example.KAV.repositories;

import com.example.KAV.models.movimiento.Movimiento;
import com.example.KAV.models.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    Page<Movimiento> findAllByUsuario(Pageable pageable, Usuario usuario);

}
