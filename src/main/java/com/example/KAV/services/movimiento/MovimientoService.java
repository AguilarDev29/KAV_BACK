package com.example.KAV.services.movimiento;

import com.example.KAV.utils.enums.TipoIngreso;
import com.example.KAV.models.movimiento.Movimiento;
import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.repositories.MovimientoRepository;
import com.example.KAV.repositories.UsuarioRepository;
import com.example.KAV.utils.exceptions.MovimientoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService implements IMovimientoService{

    private final MovimientoRepository movimientoRepository;
    private final UsuarioRepository usuarioRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, UsuarioRepository usuarioRepository) {
        this.movimientoRepository = movimientoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Movimiento getMovimiento(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoException(HttpStatus.NOT_FOUND, "Movimiento no encontrado"));
    }

    @Override
    public Page<Movimiento> getAll(Pageable pageable) {
        return movimientoRepository.findAll(pageable);
    }

    @Override
    public Page<Movimiento> getAllByUser(Pageable pageable, Usuario usuario) {
        return movimientoRepository.findAllByUsuario(pageable, usuario);
    }

    @Override
    public Page<Movimiento> getAllByUserAdmin(Pageable pageable, Long id) {
        var user = usuarioRepository.findById(id);
        if(user.isEmpty())
            throw new MovimientoException(HttpStatus.NOT_FOUND, "Usuario no encontrado");

        return movimientoRepository.findAllByUsuario(pageable, user.get());
    }

    @Override
    public Movimiento createMovimiento(Usuario usuario, Movimiento movimiento) {
        movimiento.setUsuario(usuario);
        if(usuario.getIngresoActivo())
            movimiento.setTipo(TipoIngreso.SALIDA);
        else
            movimiento.setTipo(TipoIngreso.ENTRADA);

        return movimientoRepository.save(movimiento);
    }

    @Override
    public void deleteMovimiento(Long id) {
        if(movimientoRepository.findById(id).isEmpty())
            throw new MovimientoException(HttpStatus.NOT_FOUND, "Movimiento no encontrado");

        movimientoRepository.deleteById(id);
    }
}