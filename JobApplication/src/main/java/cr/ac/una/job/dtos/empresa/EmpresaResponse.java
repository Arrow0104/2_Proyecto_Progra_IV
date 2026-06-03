package cr.ac.una.job.dtos.empresa;

import java.time.LocalDateTime;

public class EmpresaResponse {
    private Long          idEmpresa;
    private String        nombre;
    private String        telefono;
    private String        localizacion;
    private String        correoEmpresa;
    private String        descripcion;
    private boolean       active;
    private LocalDateTime createdAt;

    public EmpresaResponse() {}

    public EmpresaResponse(Long idEmpresa, String nombre, String telefono,
                            String localizacion, String correoEmpresa, String descripcion,
                            boolean active, LocalDateTime createdAt) {
        this.idEmpresa    = idEmpresa;
        this.nombre       = nombre;
        this.telefono     = telefono;
        this.localizacion = localizacion;
        this.correoEmpresa= correoEmpresa;
        this.descripcion  = descripcion;
        this.active       = active;
        this.createdAt    = createdAt;
    }

    public Long          getIdEmpresa()                        { return idEmpresa; }
    public void          setIdEmpresa(Long id)                 { this.idEmpresa = id; }
    public String        getNombre()                           { return nombre; }
    public void          setNombre(String n)                   { this.nombre = n; }
    public String        getTelefono()                         { return telefono; }
    public void          setTelefono(String t)                 { this.telefono = t; }
    public String        getLocalizacion()                     { return localizacion; }
    public void          setLocalizacion(String l)             { this.localizacion = l; }
    public String        getCorreoEmpresa()                    { return correoEmpresa; }
    public void          setCorreoEmpresa(String c)            { this.correoEmpresa = c; }
    public String        getDescripcion()                      { return descripcion; }
    public void          setDescripcion(String d)              { this.descripcion = d; }
    public boolean       isActive()                            { return active; }
    public void          setActive(boolean a)                  { this.active = a; }
    public LocalDateTime getCreatedAt()                        { return createdAt; }
    public void          setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}