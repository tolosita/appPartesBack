package co.com.partes.appPartesRest.controller;

import co.com.partes.appPartesRest.repository.ComparendoParteRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReportesController {

    @Autowired
    ComparendoParteRepository comparendoParteRepository;

    // Get a Single comparendo por Parte
    @GetMapping("/reportes/comparendos")
    public List<Map> getComparendosByParte() {
        return comparendoParteRepository.findAllComparendosRealizados();
    }
}
