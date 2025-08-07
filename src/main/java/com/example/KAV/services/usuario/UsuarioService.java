package com.example.KAV.services.usuario;

import com.example.KAV.models.usuario.Usuario;
import com.example.KAV.repositories.UsuarioRepository;
import com.example.KAV.utils.ISaveFiles;
import com.example.KAV.utils.exceptions.UsuarioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UsuarioService implements IUsuarioService, ISaveFiles {
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
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    @Override
    public Page<Usuario> getAllUsers(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Usuario createUser(Usuario usuario) {
        usuario.setUsername(usuario.getDni());
        usuario.setPassword(usuario.getDni());
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUserAdmin(Usuario usuario, long id) {
        var userToUpdate = usuarioRepository.findById(id);

        if(userToUpdate.isEmpty())
            throw new UsuarioException(HttpStatus.NOT_FOUND,
                "Usuario no encontado");

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

    @Override
    public Usuario addFoto(Long id, MultipartFile foto) throws IOException {
        if(foto.isEmpty())
            throw new RuntimeException("La imagen es requerida");
        var usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()) throw new UsuarioException(HttpStatus.NOT_FOUND, "Usuario no encontrado");

        usuario.get().setFoto(saveImagen(foto));

        return usuarioRepository.save(usuario.get());
    }

    @Override
    public void enableDisableUser(long id, boolean enable) {
        var user = usuarioRepository.findById(id);
        if(user.isEmpty())
            throw new UsuarioException(HttpStatus.NOT_FOUND, "Usuario no encontrado");

        if (enable)
            user.get().setActivo(true);
        if (!enable)
            user.get().setActivo(false);

        usuarioRepository.save(user.get());
    }

    @Override
    public void deleteUser(long id) {
        if(usuarioRepository.findById(id).isEmpty())
            throw new UsuarioException(HttpStatus.NOT_FOUND, "Usuario no encontrado");

        usuarioRepository.deleteById(id);
    }
}
