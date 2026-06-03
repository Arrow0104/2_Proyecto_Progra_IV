package cr.ac.una.job.dtos.oferente;

import jakarta.validation.constraints.NotBlank;

public class CreateOferenteRequest {

    @NotBlank private String nombre;
    @NotBlank private String apellido;
    private String nacionalidad;
    private String telefono;
    private String correoOferente;
    @NotBlank private String residencia;
    private String cvPath;

    public CreateOferenteRequest() {}

    public String getNombre()               { return nombre; }
    public void   setNombre(String n)       { this.nombre = n; }
    public String getApellido()             { return apellido; }
    public void   setApellido(String a)     { this.apellido = a; }
    public String getNacionalidad()         { return nacionalidad; }
    public void   setNacionalidad(String n) { this.nacionalidad = n; }
    public String getTelefono()             { return telefono; }
    public void   setTelefono(String t)     { this.telefono = t; }
    public String getCorreoOferente()       { return correoOferente; }
    public void   setCorreoOferente(String c){ this.correoOferente = c; }
    public String getResidencia()           { return residencia; }
    public void   setResidencia(String r)   { this.residencia = r; }
    public String getCvPath()               { return cvPath; }
    public void   setCvPath(String cv)      { this.cvPath = cv; }
}