package cr.ac.una.job.dtos.usuario;

import cr.ac.una.job.models.EstadoUsuario;
import cr.ac.una.job.models.Rol;

public class UpdateUsuarioRequest {

    private String        correo;
    private String        identificacion;
    private String        password;
    private Rol           rol;
    private EstadoUsuario estado;

    public UpdateUsuarioRequest() {}

    public UpdateUsuarioRequest(String correo, String identificacion,
                                 String password, Rol rol, EstadoUsuario estado) {
        this.correo         = correo;
        this.identificacion = identificacion;
        this.password       = password;
        this.rol            = rol;
        this.estado         = estado;
    }

    public String        getCorreo()                  { return correo; }
    public void          setCorreo(String c)          { this.correo = c; }
    public String        getIdentificacion()          { return identificacion; }
    public void          setIdentificacion(String i)  { this.identificacion = i; }
    public String        getPassword()                { return password; }
    public void          setPassword(String p)        { this.password = p; }
    public Rol           getRol()                     { return rol; }
    public void          setRol(Rol r)                { this.rol = r; }
    public EstadoUsuario getEstado()                  { return estado; }
    public void          setEstado(EstadoUsuario e)   { this.estado = e; }
}