package co.com.partes.appPartesRest.repository;

import co.com.partes.appPartesRest.model.Infractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfractorRepository extends JpaRepository<Infractor, Long> {

}
