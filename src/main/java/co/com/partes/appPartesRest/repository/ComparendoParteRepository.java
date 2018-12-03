package co.com.partes.appPartesRest.repository;

import co.com.partes.appPartesRest.model.ComparendoParte;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComparendoParteRepository extends JpaRepository<ComparendoParte, Long> {

    @Query(
            value = "select  t2.tipo as \"tipoInfraccion\", count(t1.tipo_comparendo_codigo) as \"repeticiones\"\n"
            + "from comparendos_partes t1\n"
            + "inner join tipo_comparendos  t2 on t1.tipo_comparendo_codigo = t2.codigo\n"
            + "GROUP BY t2.tipo HAVING COUNT(t1.tipo_comparendo_codigo)\n"
            + "ORDER BY COUNT(t1.tipo_comparendo_codigo) DESC",
            nativeQuery = true)
    List<Map> findAllComparendosRealizados();

}
