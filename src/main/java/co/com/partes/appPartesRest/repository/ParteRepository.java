package co.com.partes.appPartesRest.repository;

import co.com.partes.appPartesRest.model.Parte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParteRepository extends JpaRepository<Parte, Long> {

}
