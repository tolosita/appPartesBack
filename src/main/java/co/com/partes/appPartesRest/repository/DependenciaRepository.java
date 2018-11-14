package co.com.partes.appPartesRest.repository;

import co.com.partes.appPartesRest.model.Dependencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependenciaRepository extends JpaRepository<Dependencia, Long> {

}
