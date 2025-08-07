package com.example.KAV.services.usuario;

import com.example.KAV.models.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUsuarioService {
    Boolean login(String username, String password);

    Usuario getUser(Long id);

    Page<Usuario> getAllUsers(Pageable pageable);

    Usuario createUser(Usuario usuario);

    Usuario updateUserAdmin(Usuario usuario, long id);

    Usuario addFoto(Long id, MultipartFile foto) throws IOException;

    void enableDisableUser(long id, boolean enable);

    void deleteUser(long id);
}
