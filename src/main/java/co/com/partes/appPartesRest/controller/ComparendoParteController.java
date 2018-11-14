package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.model.ComparendoParte;
import co.com.partes.appPartesRest.model.Parte;
import co.com.partes.appPartesRest.model.TipoComparendo;
import co.com.partes.appPartesRest.repository.ComparendoParteRepository;
import co.com.partes.appPartesRest.repository.ParteRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ComparendoParteController {

    @Autowired
    ComparendoParteRepository comparendoParteRepository;

    @Autowired
    ParteRepository parteRepository;

    // Get a Single comparendo por Parte
    @GetMapping("/comparendosPartes/{id}")
    public List<ComparendoParte> getComparendosByParte(@PathVariable(value = "id") Long codigo) {
        return comparendoParteRepository.findAll().stream()
                .filter(comparendoParte -> comparendoParte.getParte().getCodigo().equals(codigo))
                .collect(Collectors.toList());
    }

    // Create a new comparendo por Parte
    @PutMapping("/comparendosPartes/{id}")
    public void createComparendoParte(@PathVariable(value = "id") Long codigo,
            @Valid @RequestBody List<TipoComparendo> comparendos) {

        comparendoParteRepository.findAll().stream()
                .filter(comparendoParte -> comparendoParte.getParte().getCodigo().equals(codigo))
                .forEach(cp -> comparendoParteRepository.delete(cp));
        Parte parte = parteRepository.findById(codigo).orElse(null);

        comparendos.stream().forEach(c -> {
            ComparendoParte cp = new ComparendoParte();
            cp.setTipoComparendo(c);
            cp.setParte(parte);
            comparendoParteRepository.save(cp);
        });
    }

}
