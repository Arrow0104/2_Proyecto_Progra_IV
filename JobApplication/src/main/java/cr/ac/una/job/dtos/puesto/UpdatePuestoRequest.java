package cr.ac.una.job.dtos.puesto;

import cr.ac.una.job.models.EstadoPuesto;
import cr.ac.una.job.models.TipoPublicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class UpdatePuestoRequest {

    @NotBlank(message = "El título es requerido")
    private String titulo;

    @NotBlank(message = "La descripción es requerida")
    private String descripcion;

    @NotNull(message = "El salario es requerido")
    @Positive(message = "El salario debe ser positivo")
    private BigDecimal salario;

    private EstadoPuesto estado;

    private TipoPublicacion tipoPublicacion;

    private Long idEmpresa;

    public UpdatePuestoRequest() {}

    public UpdatePuestoRequest(String titulo, String descripcion, BigDecimal salario,
                                EstadoPuesto estado, TipoPublicacion tipoPublicacion, Long idEmpresa) {
        this.titulo          = titulo;
        this.descripcion     = descripcion;
        this.salario         = salario;
        this.estado          = estado;
        this.tipoPublicacion = tipoPublicacion;
        this.idEmpresa       = idEmpresa;
    }

    public String getTitulo()                          { return titulo; }
    public void   setTitulo(String titulo)             { this.titulo = titulo; }

    public String getDescripcion()                     { return descripcion; }
    public void   setDescripcion(String descripcion)   { this.descripcion = descripcion; }

    public BigDecimal getSalario()                     { return salario; }
    public void       setSalario(BigDecimal salario)   { this.salario = salario; }

    public EstadoPuesto getEstado()                    { return estado; }
    public void         setEstado(EstadoPuesto estado) { this.estado = estado; }

    public TipoPublicacion getTipoPublicacion()                         { return tipoPublicacion; }
    public void            setTipoPublicacion(TipoPublicacion tipo)     { this.tipoPublicacion = tipo; }

    public Long getIdEmpresa()                         { return idEmpresa; }
    public void setIdEmpresa(Long idEmpresa)           { this.idEmpresa = idEmpresa; }
}