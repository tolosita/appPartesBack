package co.com.partes.appPartesRest.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "partes")
public class Parte extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @NotBlank
    @Size(max = 50)
    private String lugar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dependencia_codigo", nullable = false)
    private Dependencia dependencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "infractor_codigo", nullable = false)
    private Infractor infractor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoVehiculo_codigo", nullable = false)
    private TipoVehiculo tipoVehiculo;

    //d@NotBlank
    @Size(max = 6)
    private String placa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_codigo", nullable = false)
    private Usuario usuario;

    @NotBlank
    @Size(max = 200)
    private String descripcion;

    private Boolean estado;

    @Lob()
    private byte[] foto;
    
    @NotBlank
    @Size(max = 50)
    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public Infractor getInfractor() {
        return infractor;
    }

    public void setInfractor(Infractor infractor) {
        this.infractor = infractor;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

}
