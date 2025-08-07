package com.example.KAV.services.movimiento;

import com.example.KAV.models.movimiento.Movimiento;
import com.example.KAV.models.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMovimientoService {

    Movimiento getMovimiento(Long id);

    Page<Movimiento> getAll(Pageable pageable);

    Page<Movimiento> getAllByUser(Pageable pageable, Usuario usuario);

    Page<Movimiento> getAllByUserAdmin(Pageable pageable, Long id);

    Movimiento createMovimiento(Usuario usuario, Movimiento movimiento);

    void deleteMovimiento(Long id);
}
