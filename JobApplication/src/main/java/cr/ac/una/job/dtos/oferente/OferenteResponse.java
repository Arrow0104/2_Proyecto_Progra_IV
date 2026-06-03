package cr.ac.una.job.dtos.oferente;

import java.time.LocalDateTime;

public class OferenteResponse {
    private Long          idOferente;
    private String        nombre;
    private String        apellido;
    private String        nacionalidad;
    private String        telefono;
    private String        correoOferente;
    private String        residencia;
    private String        cvPath;
    private boolean       active;
    private LocalDateTime createdAt;

    public OferenteResponse() {}

    public OferenteResponse(Long idOferente, String nombre, String apellido,
                             String nacionalidad, String telefono, String correoOferente,
                             String residencia, String cvPath,
                             boolean active, LocalDateTime createdAt) {
        this.idOferente     = idOferente;
        this.nombre         = nombre;
        this.apellido       = apellido;
        this.nacionalidad   = nacionalidad;
        this.telefono       = telefono;
        this.correoOferente = correoOferente;
        this.residencia     = residencia;
        this.cvPath         = cvPath;
        this.active         = active;
        this.createdAt      = createdAt;
    }

    public Long          getIdOferente()                       { return idOferente; }
    public void          setIdOferente(Long id)                { this.idOferente = id; }
    public String        getNombre()                           { return nombre; }
    public void          setNombre(String n)                   { this.nombre = n; }
    public String        getApellido()                         { return apellido; }
    public void          setApellido(String a)                 { this.apellido = a; }
    public String        getNacionalidad()                     { return nacionalidad; }
    public void          setNacionalidad(String n)             { this.nacionalidad = n; }
    public String        getTelefono()                         { return telefono; }
    public void          setTelefono(String t)                 { this.telefono = t; }
    public String        getCorreoOferente()                   { return correoOferente; }
    public void          setCorreoOferente(String c)           { this.correoOferente = c; }
    public String        getResidencia()                       { return residencia; }
    public void          setResidencia(String r)               { this.residencia = r; }
    public String        getCvPath()                           { return cvPath; }
    public void          setCvPath(String cv)                  { this.cvPath = cv; }
    public boolean       isActive()                            { return active; }
    public void          setActive(boolean a)                  { this.active = a; }
    public LocalDateTime getCreatedAt()                        { return createdAt; }
    public void          setCreatedAt(LocalDateTime c)         { this.createdAt = c; }
}