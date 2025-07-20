package com.example.KAV.controllers;

import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.models.usuario.dto.DtoAltaUsuario;
import com.example.KAV.models.usuario.dto.DtoDatosUsuario;
import com.example.KAV.services.IUsuarioService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/admin/users")
public class UsuarioController {
    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<DtoDatosUsuario>> getAllUsers(Pageable pageable){
        return ResponseEntity.ok(usuarioService.getAllUsers(pageable).map(DtoDatosUsuario::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoDatosUsuario> getUserById(@PathVariable Long id){
        Usuario usuario = usuarioService.getUser(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DtoDatosUsuario(usuario));
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody DtoAltaUsuario usuario){
        Usuario createdUser = usuarioService.createUser(new Usuario(usuario));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoDatosUsuario> updateUser(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario updatedUser = usuarioService.updateUser(usuario, id);
        if(updatedUser == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DtoDatosUsuario(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
