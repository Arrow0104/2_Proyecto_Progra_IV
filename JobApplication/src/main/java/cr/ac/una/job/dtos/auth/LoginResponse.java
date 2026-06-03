package cr.ac.una.job.dtos.auth;

public class LoginResponse {
    private String token;
    private String rol;
    private Long   usuarioId;

    public LoginResponse() {}

    public LoginResponse(String token, String rol, Long usuarioId) {
        this.token     = token;
        this.rol       = rol;
        this.usuarioId = usuarioId;
    }

    public String getToken()             { return token; }
    public void   setToken(String t)     { this.token = t; }
    public String getRol()               { return rol; }
    public void   setRol(String r)       { this.rol = r; }
    public Long   getUsuarioId()         { return usuarioId; }
    public void   setUsuarioId(Long id)  { this.usuarioId = id; }
}