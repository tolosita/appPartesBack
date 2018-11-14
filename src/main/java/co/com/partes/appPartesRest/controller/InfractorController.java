package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.exception.ResourceNotFoundException;
import co.com.partes.appPartesRest.model.Infractor;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.partes.appPartesRest.repository.InfractorRepository;

@RestController
@RequestMapping("/api")
public class InfractorController {

    @Autowired
    InfractorRepository infractorRepository;

    // Get All Infractores
    @GetMapping("/infractores")
    public List<Infractor> getAllInfractores() {
        return infractorRepository.findAll();
    }

    // Get a Single Infractor
    @GetMapping("/infractores/{id}")
    public Infractor getInfractorById(@PathVariable(value = "id") Long identificacion) {
        return infractorRepository.findById(identificacion)
                .orElseThrow(() -> new ResourceNotFoundException("Infractor", "id", identificacion));
    }

    // Create a new Infractor
    @PostMapping("/infractores")
    public Infractor createInfractor(@Valid @RequestBody Infractor infractor) {
        return infractorRepository.save(infractor);
    }

    // Update a Infractor
    @PutMapping("/infractores/{id}")
    public Infractor updateInfractor(@PathVariable(value = "id") Long identificacion,
            @Valid @RequestBody Infractor infractorDetails) {

        Infractor infractor = infractorRepository.findById(identificacion)
                .orElseThrow(() -> new ResourceNotFoundException("Infractor", "id", identificacion));

        infractor.setIdentificacion(infractorDetails.getIdentificacion());
        infractor.setNombre(infractorDetails.getNombre());
        infractor.setApellidos(infractorDetails.getApellidos());
        infractor.setGrado(infractorDetails.getGrado());
        infractor.setTipoDocumento(infractorDetails.getTipoDocumento());

        Infractor updatedInfractor = infractorRepository.save(infractor);
        return updatedInfractor;
    }

    // Delete a Infractor
    @DeleteMapping("/infractores/{id}")
    public ResponseEntity<?> deleteInfractor(@PathVariable(value = "id") Long identificacion) {
        Infractor infractor = infractorRepository.findById(identificacion)
                .orElseThrow(() -> new ResourceNotFoundException("Infractor", "id", identificacion));

        infractorRepository.delete(infractor);

        return ResponseEntity.ok().build();
    }

}
