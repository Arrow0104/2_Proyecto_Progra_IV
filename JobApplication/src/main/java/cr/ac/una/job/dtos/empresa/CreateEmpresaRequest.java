package cr.ac.una.job.dtos.empresa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateEmpresaRequest {

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 2, max = 100)
    private String nombre;

    @NotBlank(message = "El teléfono es requerido")
    @Size(min = 8, max = 20)
    private String telefono;

    private String localizacion;
    private String correoEmpresa;
    private String descripcion;

    public CreateEmpresaRequest() {}

    public CreateEmpresaRequest(String nombre, String telefono,
                                String localizacion, String correoEmpresa, String descripcion) {
        this.nombre        = nombre;
        this.telefono      = telefono;
        this.localizacion  = localizacion;
        this.correoEmpresa = correoEmpresa;
        this.descripcion   = descripcion;
    }

    public String getNombre()                      { return nombre; }
    public void   setNombre(String n)              { this.nombre = n; }
    public String getTelefono()                    { return telefono; }
    public void   setTelefono(String t)            { this.telefono = t; }
    public String getLocalizacion()                { return localizacion; }
    public void   setLocalizacion(String l)        { this.localizacion = l; }
    public String getCorreoEmpresa()               { return correoEmpresa; }
    public void   setCorreoEmpresa(String c)       { this.correoEmpresa = c; }
    public String getDescripcion()                 { return descripcion; }
    public void   setDescripcion(String d)         { this.descripcion = d; }
}