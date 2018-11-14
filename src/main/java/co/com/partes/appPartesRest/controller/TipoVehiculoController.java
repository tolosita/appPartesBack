package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.exception.ResourceNotFoundException;
import co.com.partes.appPartesRest.model.TipoVehiculo;
import co.com.partes.appPartesRest.repository.TipoVehiculoRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TipoVehiculoController {

    @Autowired
    TipoVehiculoRepository tipoVehiculoRepository;

    // Get All TipoVehiculos
    @GetMapping("/tipoVehiculos")
    public List<TipoVehiculo> getAllTipoVehiculos() {
        return tipoVehiculoRepository.findAll();
    }

    // Get a Single TipoVehiculo
    @GetMapping("/tipoVehiculos/{id}")
    public TipoVehiculo getTipoVehiculoById(@PathVariable(value = "id") Long codigo) {
        return tipoVehiculoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("TipoVehiculo", "id", codigo));
    }

    // Create a new TipoVehiculo
    @PostMapping("/tipoVehiculos")
    public TipoVehiculo createTipoVehiculo(@Valid @RequestBody TipoVehiculo tipoVehiculo) {
        return tipoVehiculoRepository.save(tipoVehiculo);
    }

    // Update a TipoVehiculo
    @PutMapping("/tipoVehiculos/{id}")
    public TipoVehiculo updateTipoVehiculo(@PathVariable(value = "id") Long codigo,
            @Valid @RequestBody TipoVehiculo tipoVehiculoDetails) {

        TipoVehiculo tipoVehiculo = tipoVehiculoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("TipoVehiculo", "id", codigo));

        tipoVehiculo.setTipo(tipoVehiculoDetails.getTipo());

        TipoVehiculo updatedTipoVehiculo = tipoVehiculoRepository.save(tipoVehiculo);
        return updatedTipoVehiculo;
    }

    // Delete a TipoVehiculo
    @DeleteMapping("/tipoVehiculos/{id}")
    public ResponseEntity<?> deleteTipoVehiculo(@PathVariable(value = "id") Long codigo) {
        TipoVehiculo tipoVehiculo = tipoVehiculoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("TipoVehiculo", "id", codigo));

        tipoVehiculoRepository.delete(tipoVehiculo);

        return ResponseEntity.ok().build();
    }

}
