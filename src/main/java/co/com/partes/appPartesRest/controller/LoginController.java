package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.configuration.Mail;
import co.com.partes.appPartesRest.model.Message;
import co.com.partes.appPartesRest.model.Usuario;
import co.com.partes.appPartesRest.repository.UsuarioRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    Mail mail;

    // Login of a user
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario user) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario u = usuarios.stream()
                .filter(usuario -> usuario.getUsuario().equals(user.getUsuario()) && usuario.getClave().equals(user.getClave()) && usuario.getEstado() == true)
                .findAny()
                .orElse(null);

        return u;
    }

    // Recuperar clave of a user
    @PostMapping("/recuperar")
    public Message recuperar(@RequestBody Usuario user) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario u = usuarios.stream()
                .filter(usuario -> usuario.getUsuario().equals(user.getUsuario()) && usuario.getCorreo().equals(user.getCorreo()))
                .findAny()
                .orElse(null);

        if (u != null) {
            try {
                mail.sendSimpleMessage(u.getCorreo(), "Recuperar Clave", "Su clave es: " + u.getClave());
                return new Message("Se ha enviado la clave al correo");
            } catch (MailException exception) {
                exception.printStackTrace();
                return new Message("Ha ocurrido un error al enviar la clave al correo");
            }
        } else {
            return new Message("El usuario no esta registrado");
        }
    }

}
