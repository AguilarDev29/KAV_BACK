package com.example.KAV.services;

import com.example.KAV.models.actividad.Actividad;
import com.example.KAV.models.horario.Horario;
import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.repositories.ActividadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                -> new EntityNotFoundException("Actividad no encontrada"));
    }

    @Override
    public Actividad createActividad(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    @Override
    public Actividad updateActividad(Actividad actividad, Long id) {
        var actividadToUpdate = actividadRepository.findById(id);

        if(actividadToUpdate.isPresent()){
            if(actividad.getTitulo() != null) actividadToUpdate.get()
                    .setTitulo(actividad.getTitulo());
            if(actividad.getDescripcion() != null) actividadToUpdate.get()
                    .setDescripcion(actividad.getDescripcion());
            if(actividad.getDuracion() != null) actividadToUpdate.get()
                    .setDuracion(actividad.getDuracion());

            return actividadRepository.save(actividad);
        }
        return null;
    }

    @Override
    public Actividad addOrRemoveInstructor(Usuario instructor, Long id, Boolean add) {
        var actividad = actividadRepository.findById(id);
        if(actividad.isPresent()){
            if(!add) actividad.get().getInstructores().remove(instructor);
            else actividad.get().getInstructores().add(instructor);
            return actividadRepository.save(actividad.get());
        }
        return null;
    }

    @Override
    public Actividad addOrRemoveHorario(Horario horario, Long id, Boolean add) {
        var actividad = actividadRepository.findById(id);
        if(actividad.isPresent()){
            if(!add) actividad.get().getHorarios().remove(horario);
            else actividad.get().getHorarios().add(horario);
            return actividadRepository.save(actividad.get());
        }
        return null;
    }

    @Override
    public void logicalDeleteActividad(Long id) {
        var actividad = actividadRepository.findById(id);
        if(actividad.isPresent()) {
            actividad.get().setActivo(false);
            actividadRepository.save(actividad.get());
        }
    }

    @Override
    public void deleteActividad(Long id) {
        actividadRepository.deleteById(id);
    }
}
