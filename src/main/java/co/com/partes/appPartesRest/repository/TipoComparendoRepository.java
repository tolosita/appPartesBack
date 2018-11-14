package co.com.partes.appPartesRest.repository;

import co.com.partes.appPartesRest.model.TipoComparendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoComparendoRepository extends JpaRepository<TipoComparendo, Long> {

}
