package com.example.KAV.services;

import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Boolean login(String username, String password) {
        return null;
    }

    @Override
    public Usuario getUser(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @Override
    public Page<Usuario> getAllUsers(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Usuario createUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUser(Usuario usuario, long id) {
        var userToUpdate = usuarioRepository.findById(id);

        if(userToUpdate.isPresent()){
            if(usuario.getApellido() != null) userToUpdate.get()
                    .setApellido(usuario.getApellido());
            if(usuario.getNombre() != null) userToUpdate.get()
                    .setNombre(usuario.getNombre());
            if(usuario.getDni() != null) userToUpdate.get()
                    .setDni(usuario.getDni());
            if(usuario.getSexo() != null) userToUpdate.get()
                    .setSexo(usuario.getSexo());
            if(usuario.getFechaNacimiento() != null) userToUpdate.get()
                    .setFechaNacimiento(usuario.getFechaNacimiento());
            if(usuario.getEmail() != null) userToUpdate.get()
                    .setEmail(usuario.getEmail());
            if(usuario.getTelefono() != null) userToUpdate.get()
                    .setTelefono(usuario.getTelefono());
            if(usuario.getRol() != null) userToUpdate.get()
                    .setRol(usuario.getRol());

            return usuarioRepository.save(userToUpdate.get());
        }
        return null;
    }

    @Override
    public void deleteUser(long id) {
        usuarioRepository.deleteById(id);
    }
}
