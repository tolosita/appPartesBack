package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.exception.ResourceNotFoundException;
import co.com.partes.appPartesRest.model.Dependencia;
import co.com.partes.appPartesRest.repository.DependenciaRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DependenciaController {

    @Autowired
    DependenciaRepository dependenciaRepository;

    // Get All Dependencias
    @GetMapping("/dependencias")
    public List<Dependencia> getAllDependencias() {
        return dependenciaRepository.findAll();
    }

    // Get a Single Dependencia
    @GetMapping("/dependencias/{id}")
    public Dependencia getDependenciaById(@PathVariable(value = "id") Long id) {
        return dependenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependencia", "id", id));
    }

    // Create a new Dependencia
    @PostMapping("/dependencias")
    public Dependencia createDependencia(@Valid @RequestBody Dependencia dependencia) {
        return dependenciaRepository.save(dependencia);
    }

    // Update a Dependencia
    @PutMapping("/dependencias/{id}")
    public Dependencia updateDependencia(@PathVariable(value = "id") Long id,
            @Valid @RequestBody Dependencia dependenciaDetails) {

        Dependencia dependencia = dependenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependencia", "id", id));

        dependencia.setAbreviatura(dependenciaDetails.getAbreviatura());
        dependencia.setNombre(dependenciaDetails.getNombre());

        Dependencia updatedDependencia = dependenciaRepository.save(dependencia);
        return updatedDependencia;
    }

    // Delete a Dependencia
    @DeleteMapping("/dependencias/{id}")
    public ResponseEntity<?> deleteDependencia(@PathVariable(value = "id") Long id) {
        Dependencia dependencia = dependenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependencia", "id", id));

        dependenciaRepository.delete(dependencia);

        return ResponseEntity.ok().build();
    }

}
