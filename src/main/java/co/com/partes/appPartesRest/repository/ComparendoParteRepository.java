package co.com.partes.appPartesRest.repository;

import co.com.partes.appPartesRest.model.ComparendoParte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComparendoParteRepository extends JpaRepository<ComparendoParte, Long> {

}
