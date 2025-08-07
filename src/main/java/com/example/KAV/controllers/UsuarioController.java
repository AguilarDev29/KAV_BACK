package com.example.KAV.controllers;

import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.models.usuario.dto.DtoAltaUsuario;
import com.example.KAV.models.usuario.dto.DtoDatosUsuario;
import com.example.KAV.services.usuario.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

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
        return ResponseEntity.ok(new DtoDatosUsuario(usuario));
    }

    @PostMapping
    public ResponseEntity<DtoDatosUsuario> createUser(@RequestBody @Valid DtoAltaUsuario usuario){
        Usuario createdUser = usuarioService.createUser(new Usuario(usuario));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(new DtoDatosUsuario(createdUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoDatosUsuario> updateUser(@PathVariable Long id,
                                                      @RequestBody @Valid DtoAltaUsuario usuario){
        return ResponseEntity.ok(new DtoDatosUsuario(usuarioService.updateUserAdmin(new Usuario(usuario), id)));
    }

    @PatchMapping("/add/foto/{id}")
    public ResponseEntity<DtoDatosUsuario> addFoto(@PathVariable Long id,
                                                   @RequestParam MultipartFile foto) throws IOException {
        return ResponseEntity.ok(new DtoDatosUsuario(usuarioService.addFoto(id, foto)));
    }

    @PatchMapping("/enable/{id}")
    public ResponseEntity<Map<String, String>> enableUser(@PathVariable Long id){
        var user = usuarioService.getUser(id);
        usuarioService.enableDisableUser(id, true);
        return ResponseEntity.ok(Map.of("Message", "Usuario activado exitosamente"));
    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity<Map<String, String>> disableUser(@PathVariable Long id){
        var user = usuarioService.getUser(id);
        usuarioService.enableDisableUser(id, false);
        return ResponseEntity.ok(Map.of("Message", "Usuario desactivado exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
