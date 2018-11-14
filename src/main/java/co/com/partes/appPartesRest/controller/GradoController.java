package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.exception.ResourceNotFoundException;
import co.com.partes.appPartesRest.model.Grado;
import co.com.partes.appPartesRest.repository.GradoRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GradoController {

    @Autowired
    GradoRepository gradoRepository;

    // Get All Grados
    @GetMapping("/grados")
    public List<Grado> getAllGrados() {
        return gradoRepository.findAll();
    }

    // Get a Single Grado
    @GetMapping("/grados/{id}")
    public Grado getGradoById(@PathVariable(value = "id") Long codigo) {
        return gradoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Grado", "id", codigo));
    }

    // Create a new Grado
    @PostMapping("/grados")
    public Grado createGrado(@Valid @RequestBody Grado grado) {
        return gradoRepository.save(grado);
    }

    // Update a Grado
    @PutMapping("/grados/{id}")
    public Grado updateGrado(@PathVariable(value = "id") Long codigo,
            @Valid @RequestBody Grado gradoDetails) {

        Grado grado = gradoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Grado", "id", codigo));

        grado.setNombre(gradoDetails.getNombre());

        Grado updatedGrado = gradoRepository.save(grado);
        return updatedGrado;
    }

    // Delete a Grado
    @DeleteMapping("/grados/{id}")
    public ResponseEntity<?> deleteGrado(@PathVariable(value = "id") Long codigo) {
        Grado grado = gradoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Grado", "id", codigo));

        gradoRepository.delete(grado);

        return ResponseEntity.ok().build();
    }

}
