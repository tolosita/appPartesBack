package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.configuration.Mail;
import co.com.partes.appPartesRest.exception.ResourceNotFoundException;
import co.com.partes.appPartesRest.model.Message;
import co.com.partes.appPartesRest.model.Parte;
import co.com.partes.appPartesRest.repository.ParteRepository;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ParteController {

    @Autowired
    ParteRepository parteRepository;

    @Autowired
    Mail mail;

    // Get All Partes
    @GetMapping("/partes")
    public List<Parte> getAllPartes() {
        return parteRepository.findAll(new Sort(Sort.Direction.DESC, "fecha"));
    }

    // Get a Single Parte
    @GetMapping("/partes/{id}")
    public Parte getParteById(@PathVariable(value = "id") Long codigo) {
        return parteRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Parte", "id", codigo));
    }

    // Create a new Parte
    @PostMapping("/partes")
    public Parte createParte(@Valid @RequestBody Parte parte) {
        parte.setEstado(Boolean.TRUE);
        Parte newParte = parteRepository.save(parte);
        notificar(newParte);
        return newParte;
    }

    // Update a Parte
    @PutMapping("/partes/{id}")
    public Parte updateParte(@PathVariable(value = "id") Long codigo,
            @Valid @RequestBody Parte parteDetails) {

        Parte parte = parteRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Parte", "id", codigo));

        parte.setFecha(parteDetails.getFecha());
        parte.setLugar(parteDetails.getLugar());
        parte.setDependencia(parteDetails.getDependencia());
        parte.setInfractor(parteDetails.getInfractor());
        parte.setTipoVehiculo(parteDetails.getTipoVehiculo());
        parte.setPlaca(parteDetails.getPlaca());
        parte.setUsuario(parteDetails.getUsuario());
        parte.setDescripcion(parteDetails.getDescripcion());
        parte.setCorreo(parteDetails.getCorreo());

        Parte updatedParte = parteRepository.save(parte);
        notificar(updatedParte);

        return updatedParte;
    }

    // Delete a Parte
    @DeleteMapping("/partes/{id}")
    public ResponseEntity<?> deleteParte(@PathVariable(value = "id") Long codigo) {
        Parte parte = parteRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Parte", "id", codigo));

        parte.setEstado(Boolean.FALSE);
        parteRepository.save(parte);

        return ResponseEntity.ok().build();
    }

    // Create a new imagen to Parte
    @PostMapping("/image/{id}")
    public Parte createImage(@PathVariable(value = "id") Long codigo,
            @RequestParam(value = "file", required = false) MultipartFile foto) throws IOException {
        Parte parte = parteRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Parte", "id", codigo));

        if (foto != null) {
            parte.setFoto(foto.getBytes());
        }

        return parteRepository.save(parte);
    }

    // Send a notification
    @PostMapping("/notificar")
    public Message notificar(@RequestBody Parte item) {

        if (item != null) {
            System.out.println(item);

            try {
                mail.sendSimpleMessage(item.getCorreo(), "Notificación de nuevo comparendo",
                        "Señor(a) (" + item.getInfractor().getGrado().getNombre() + ") " + item.getInfractor().getNombre() + " " + item.getInfractor().getApellidos()
                        + ", se le ha elaborado el Comparendo número (" + item.getCodigo()
                        + ") por parte de la Policía Militar Aérea. \n Deberá presentarse ante"
                        + " el Segundo Comando del CACOM-4.",
                        "mario_ruiz23151@elpoli.edu.co");
                return new Message("Se ha enviado la notificación al correo");
            } catch (MailException exception) {
                exception.printStackTrace();
                return new Message("Ha ocurrido un error al enviar la notificación");
            }
        } else {
            return new Message("Error en el parte");
        }
    }
}
