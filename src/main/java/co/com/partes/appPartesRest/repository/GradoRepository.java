package co.com.partes.appPartesRest.repository;

import co.com.partes.appPartesRest.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoRepository extends JpaRepository<Grado, Long> {

}
