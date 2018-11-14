package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.exception.ResourceNotFoundException;
import co.com.partes.appPartesRest.model.TipoComparendo;
import co.com.partes.appPartesRest.repository.TipoComparendoRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TipoComparendoController {

    @Autowired
    TipoComparendoRepository tipoComparendoRepository;

    // Get All TipoComparendos
    @GetMapping("/tipoComparendos")
    public List<TipoComparendo> getAllTipoComparendos() {
        return tipoComparendoRepository.findAll();
    }

    // Get a Single TipoComparendo
    @GetMapping("/tipoComparendos/{id}")
    public TipoComparendo getTipoComparendoById(@PathVariable(value = "id") Long codigo) {
        return tipoComparendoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("TipoComparendo", "id", codigo));
    }

    // Create a new TipoComparendo
    @PostMapping("/tipoComparendos")
    public TipoComparendo createTipoComparendo(@Valid @RequestBody TipoComparendo tipoComparendo) {
        return tipoComparendoRepository.save(tipoComparendo);
    }

    // Update a TipoComparendo
    @PutMapping("/tipoComparendos/{id}")
    public TipoComparendo updateTipoComparendo(@PathVariable(value = "id") Long codigo,
            @Valid @RequestBody TipoComparendo tipoComparendoDetails) {

        TipoComparendo tipoComparendo = tipoComparendoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("TipoComparendo", "id", codigo));

        tipoComparendo.setTipo(tipoComparendoDetails.getTipo());

        TipoComparendo updatedTipoComparendo = tipoComparendoRepository.save(tipoComparendo);
        return updatedTipoComparendo;
    }

    // Delete a TipoComparendo
    @DeleteMapping("/tipoComparendos/{id}")
    public ResponseEntity<?> deleteTipoComparendo(@PathVariable(value = "id") Long codigo) {
        TipoComparendo tipoComparendo = tipoComparendoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("TipoComparendo", "id", codigo));

        tipoComparendoRepository.delete(tipoComparendo);

        return ResponseEntity.ok().build();
    }

}
