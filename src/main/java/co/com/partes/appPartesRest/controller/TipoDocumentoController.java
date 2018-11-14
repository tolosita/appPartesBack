package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.exception.ResourceNotFoundException;
import co.com.partes.appPartesRest.model.TipoDocumento;
import co.com.partes.appPartesRest.repository.TipoDocumentoRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TipoDocumentoController {

    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    // Get All TipoDocumentos
    @GetMapping("/tipoDocumentos")
    public List<TipoDocumento> getAllTipoDocumentos() {
        return tipoDocumentoRepository.findAll();
    }

    // Get a Single TipoDocumento
    @GetMapping("/tipoDocumentos/{id}")
    public TipoDocumento getTipoDocumentoById(@PathVariable(value = "id") Long codigo) {
        return tipoDocumentoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("TipoDocumento", "id", codigo));
    }

    // Create a new TipoDocumento
    @PostMapping("/tipoDocumentos")
    public TipoDocumento createTipoDocumento(@Valid @RequestBody TipoDocumento tipoDocumento) {
        return tipoDocumentoRepository.save(tipoDocumento);
    }

    // Update a TipoDocumento
    @PutMapping("/tipoDocumentos/{id}")
    public TipoDocumento updateTipoDocumento(@PathVariable(value = "id") Long codigo,
            @Valid @RequestBody TipoDocumento tipoDocumentoDetails) {

        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("TipoDocumento", "id", codigo));

        tipoDocumento.setTipo(tipoDocumentoDetails.getTipo());

        TipoDocumento updatedTipoDocumento = tipoDocumentoRepository.save(tipoDocumento);
        return updatedTipoDocumento;
    }

    // Delete a TipoDocumento
    @DeleteMapping("/tipoDocumentos/{id}")
    public ResponseEntity<?> deleteTipoDocumento(@PathVariable(value = "id") Long codigo) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("TipoDocumento", "id", codigo));

        tipoDocumentoRepository.delete(tipoDocumento);

        return ResponseEntity.ok().build();
    }

}
