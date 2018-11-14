package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.exception.ResourceNotFoundException;
import co.com.partes.appPartesRest.model.Usuario;
import co.com.partes.appPartesRest.repository.UsuarioRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    // Get All Usuarios
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Get a Single Usuario
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Long codigo) {
        return usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));
    }

    // Create a new Usuario
    @PostMapping("/usuarios")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        usuario.setEstado(true);
        return usuarioRepository.save(usuario);
    }

    // Update a Usuario
    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable(value = "id") Long codigo,
            @Valid @RequestBody Usuario usuarioDetails) {

        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));

        usuario.setIdentificacion(usuarioDetails.getIdentificacion());
        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellidos(usuarioDetails.getApellidos());
        usuario.setUsuario(usuarioDetails.getUsuario());
        usuario.setClave(usuarioDetails.getClave());
        usuario.setCorreo(usuarioDetails.getCorreo());
        usuario.setTipo(usuarioDetails.isTipo());
        usuario.setEstado(usuarioDetails.getEstado());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return updatedUsuario;
    }

    // Delete a Usuario
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long codigo) {
        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigo));

        usuarioRepository.delete(usuario);

        return ResponseEntity.ok().build();
    }

}
