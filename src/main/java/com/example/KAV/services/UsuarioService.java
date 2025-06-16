package com.example.KAV.services;

import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.repositories.UsuarioRepository;
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
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario createUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUser(Usuario usuario, long id) {
        var userToUpdate = usuarioRepository.findById(id);

        if(userToUpdate.isPresent()){
            if(usuario.getUsername() != null) userToUpdate.get()
                    .setUsername(usuario.getUsername());
            if(usuario.getPassword() != null) userToUpdate.get()
                    .setPassword(usuario.getPassword());
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
