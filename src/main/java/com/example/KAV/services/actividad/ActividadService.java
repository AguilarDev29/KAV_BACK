package com.example.KAV.services.actividad;

import com.example.KAV.models.actividad.Actividad;
import com.example.KAV.models.horario.Horario;
import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.repositories.ActividadRepository;
import com.example.KAV.utils.exceptions.ActividadException;
import com.example.KAV.utils.exceptions.HorarioException;
import com.example.KAV.utils.exceptions.UsuarioException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ActividadService implements IActividadService{

    private final ActividadRepository actividadRepository;

    public ActividadService(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    @Override
    public Page<Actividad> getAll(Pageable pageable) {
        return actividadRepository.findAll(pageable);
    }

    @Override
    public Page<Actividad> getAllTrue(Pageable pageable) {
        return actividadRepository.findByActivoTrue(pageable);
    }

    @Override
    public Actividad getActividad(Long id) {
        return actividadRepository.findById(id).orElseThrow(()
                -> new ActividadException(HttpStatus.NOT_FOUND, "Actividad no encontrada"));
    }

    @Override
    public Actividad createActividad(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    @Override
    public Actividad updateActividad(Actividad actividad, Long id) {
        var actividadToUpdate = actividadRepository.findById(id);

        if(actividadToUpdate.isEmpty())
            throw new ActividadException(HttpStatus.NOT_FOUND, "Error al actualizar actividad");

        if(actividad.getTitulo() != null) actividadToUpdate.get()
                .setTitulo(actividad.getTitulo());
        if(actividad.getDescripcion() != null) actividadToUpdate.get()
                .setDescripcion(actividad.getDescripcion());
        if(actividad.getDuracion() != null) actividadToUpdate.get()
                .setDuracion(actividad.getDuracion());

        return actividadRepository.save(actividadToUpdate.get());
    }

    @Override
    public Actividad addOrRemoveInstructor(Usuario instructor, Long id, Boolean add) {
        var actividad = actividadRepository.findById(id);

        if(actividad.isEmpty())
            throw new ActividadException(HttpStatus.NOT_FOUND, "Actividad no encontrada");

        if(instructor == null)
            throw new UsuarioException(HttpStatus.BAD_REQUEST, "El instructor es requerido");

        if(!add)
            actividad.get().getInstructores().remove(instructor);
        else
            actividad.get().getInstructores().add(instructor);

        return actividadRepository.save(actividad.get());
    }

    @Override
    public Actividad addOrRemoveHorario(Horario horario, Long id, Boolean add) {
        var actividad = actividadRepository.findById(id);

        if(actividad.isEmpty())
            throw new ActividadException(HttpStatus.NOT_FOUND, "Actividad no encontrada");
        if(horario == null)
            throw new HorarioException(HttpStatus.BAD_REQUEST, "El horario es requerido");

        if(!add)
            actividad.get().getHorarios().remove(horario);
        else
            actividad.get().getHorarios().add(horario);

        return actividadRepository.save(actividad.get());
    }

    @Override
    public Actividad addOrRemoveUsuario(Usuario usuario, Long id, Boolean add) {
        var actividad = actividadRepository.findById(id);

        if(actividad.isEmpty())
            throw new ActividadException(HttpStatus.NOT_FOUND, "Actividad no encontrada");
        if(usuario == null)
            throw new UsuarioException(HttpStatus.BAD_REQUEST, "El usuario es requerido");

        if(!add)
            actividad.get().getUsuarios().remove(usuario);
        else
            actividad.get().getUsuarios().add(usuario);

        return actividadRepository.save(actividad.get());
    }

    @Override
    public void logicalDeleteActividad(Long id) {
        var actividad = actividadRepository.findById(id);

        if(actividad.isEmpty())
            throw new ActividadException(HttpStatus.NOT_FOUND, "Actividad no encontrada");

        actividad.get().setActivo(false);
        actividadRepository.save(actividad.get());
    }

    @Override
    public void deleteActividad(Long id) {
        if(actividadRepository.findById(id).isEmpty())
            throw new ActividadException(HttpStatus.NOT_FOUND, "Actividad no encontrada");

        actividadRepository.deleteById(id);
    }
}
