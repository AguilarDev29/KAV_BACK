package com.example.KAV.services;

import com.example.KAV.models.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuarioService {
    Boolean login(String username, String password);

    Usuario getUser(Long id);

    Page<Usuario> getAllUsers(Pageable pageable);

    Usuario createUser(Usuario usuario);

    Usuario updateUser(Usuario usuario, long id);

    void deleteUser(long id);
}
