package com.example.KAV.services;

import com.example.KAV.models.usuario.Usuario;

import java.util.List;

public interface IUsuarioService {
    Boolean login(String username, String password);

    Usuario getUser(Long id);

    List<Usuario> getAllUsers();

    Usuario createUser(Usuario usuario);

    Usuario updateUser(Usuario usuario, long id);

    void deleteUser(long id);
}
