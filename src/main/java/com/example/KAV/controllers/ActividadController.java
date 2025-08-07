package com.example.KAV.controllers;

import com.example.KAV.models.actividad.Actividad;
import com.example.KAV.models.horario.Horario;
import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.services.actividad.IActividadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/v1/actividades")
public class ActividadController {

    private final IActividadService actividadService;

    public ActividadController(IActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping
    public ResponseEntity<Page<Actividad>> getAll(Pageable pageable){
        return ResponseEntity.ok(actividadService.getAll(pageable));
    }
    @GetMapping("/active")
    public ResponseEntity<Page<Actividad>> getAllActive(Pageable pageable) {
        return ResponseEntity.ok(actividadService.getAllTrue(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actividad> getActividad(Long id){
        var actividad = actividadService.getActividad(id);
        if(actividad == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actividad);
    }

    @PostMapping
    public ResponseEntity<Actividad> createActividad(@RequestBody Actividad actividad) {
        var createdActividad = actividadService.createActividad(actividad);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdActividad.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdActividad);

    }

    @PutMapping("{id}")
    public ResponseEntity<Actividad> updateActividad(Actividad actividad, @PathVariable Long id){
        var actividadToUpdate = actividadService.getActividad(id);
        if(actividadToUpdate == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actividadService.updateActividad(actividad, id));
    }

    @PatchMapping("/instructor/add")
    public ResponseEntity<Map<String, String>> addInstructor(@RequestBody Usuario instructor, @PathVariable Long id){
        var actividad = actividadService.addOrRemoveInstructor(instructor, id, true);
        if(actividad == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("Message", "Instructor agregado exitosamente"));
    }

    @PatchMapping("/instructor/remove")
    public ResponseEntity<Map<String, String>> removeInstructor(@RequestBody Usuario instructor, @PathVariable Long id){
        var actividad = actividadService.addOrRemoveInstructor(instructor, id, false);
        if(actividad == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("Message", "Instructor quitado exitosamente"));
    }

    @PatchMapping("/horarios/add")
    public ResponseEntity<Map<String, String>> addHorario(@RequestBody Horario horario, @PathVariable Long id){
        var actividad = actividadService.addOrRemoveHorario(horario, id, true);
        if(actividad == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("Message", "Horario agregado exitosamente"));
    }

    @PatchMapping("/horarios/remove")
    public ResponseEntity<Map<String, String>> removeHorario(@RequestBody Horario horario, @PathVariable Long id){
        var actividad = actividadService.addOrRemoveHorario(horario, id, false);
        if(actividad == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("Message", "Horario quitado exitosamente"));
    }

    @PatchMapping("/usuarios/add")
    public ResponseEntity<Map<String, String>> addUsuario(@RequestBody Usuario usuario, @PathVariable Long id){
        var actividad = actividadService.addOrRemoveUsuario(usuario, id, true);
        if(actividad == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("Message", "Usuario agregado exitosamente"));
    }

    @PatchMapping("/usuarios/remove")
    public ResponseEntity<Map<String, String>> removeUsuario(@RequestBody Usuario usuario, @PathVariable Long id){
        var actividad = actividadService.addOrRemoveInstructor(usuario, id, false);
        if(actividad == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("Message", "Usuario quitado exitosamente"));
    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity<Void> disableActividad(@PathVariable Long id){
        actividadService.logicalDeleteActividad(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActividad(@PathVariable Long id){
        actividadService.deleteActividad(id);
        return ResponseEntity.noContent().build();
    }
}
