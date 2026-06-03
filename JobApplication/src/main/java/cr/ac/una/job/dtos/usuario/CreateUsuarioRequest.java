package cr.ac.una.job.dtos.usuario;

import cr.ac.una.job.models.EstadoUsuario;
import cr.ac.una.job.models.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUsuarioRequest {

    @NotBlank private String correo;
    @NotBlank private String identificacion;
    @NotBlank private String password;
    @NotNull  private Rol    rol;
    private EstadoUsuario    estado = EstadoUsuario.ACTIVO;

    public CreateUsuarioRequest() {}

    public CreateUsuarioRequest(String correo, String identificacion,
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