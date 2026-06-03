package cr.ac.una.job.dtos.oferente;

import jakarta.validation.constraints.NotBlank;

public class UpdateOferenteRequest {

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    private String nacionalidad;
    private String telefono;
    private String correoOferente;

    @NotBlank(message = "La residencia es requerida")
    private String residencia;

    private String cvPath;

    public UpdateOferenteRequest() {}

    public UpdateOferenteRequest(String nombre, String apellido, String nacionalidad,
                                  String telefono, String correoOferente,
                                  String residencia, String cvPath) {
        this.nombre         = nombre;
        this.apellido       = apellido;
        this.nacionalidad   = nacionalidad;
        this.telefono       = telefono;
        this.correoOferente = correoOferente;
        this.residencia     = residencia;
        this.cvPath         = cvPath;
    }

    public String getNombre()                    { return nombre; }
    public void   setNombre(String n)            { this.nombre = n; }
    public String getApellido()                  { return apellido; }
    public void   setApellido(String a)          { this.apellido = a; }
    public String getNacionalidad()              { return nacionalidad; }
    public void   setNacionalidad(String n)      { this.nacionalidad = n; }
    public String getTelefono()                  { return telefono; }
    public void   setTelefono(String t)          { this.telefono = t; }
    public String getCorreoOferente()            { return correoOferente; }
    public void   setCorreoOferente(String c)    { this.correoOferente = c; }
    public String getResidencia()                { return residencia; }
    public void   setResidencia(String r)        { this.residencia = r; }
    public String getCvPath()                    { return cvPath; }
    public void   setCvPath(String c)            { this.cvPath = c; }
}