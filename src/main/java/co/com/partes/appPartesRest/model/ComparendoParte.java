package co.com.partes.appPartesRest.model;

import javax.persistence.*;

@Entity
@Table(name = "comparendos_partes")
public class ComparendoParte extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoComparendo_codigo", nullable = false)
    private TipoComparendo tipoComparendo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parte_codigo", nullable = false)
    private Parte parte;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public TipoComparendo getTipoComparendo() {
        return tipoComparendo;
    }

    public void setTipoComparendo(TipoComparendo tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }

}
