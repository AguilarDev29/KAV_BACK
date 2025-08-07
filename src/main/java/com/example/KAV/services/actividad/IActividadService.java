package com.example.KAV.services.actividad;

import com.example.KAV.models.actividad.Actividad;
import com.example.KAV.models.horario.Horario;
import com.example.KAV.models.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IActividadService {

    Page<Actividad> getAll(Pageable pageable);

    Page<Actividad> getAllTrue(Pageable pageable);

    Actividad getActividad(Long id);

    Actividad createActividad(Actividad actividad);

    Actividad updateActividad(Actividad actividad, Long id);

    Actividad addOrRemoveInstructor(Usuario instructor, Long id, Boolean add);

    Actividad addOrRemoveHorario(Horario horario, Long id, Boolean add);

    Actividad addOrRemoveUsuario(Usuario usuario, Long id, Boolean add);

    void logicalDeleteActividad(Long id);

    void deleteActividad(Long id);

}
