package cr.ac.una.job.dtos.auth;

public class LoginRequest {
    private String correo;
    private String password;

    public LoginRequest() {}

    public LoginRequest(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public String getCorreo()            { return correo; }
    public void   setCorreo(String c)    { this.correo = c; }
    public String getPassword()          { return password; }
    public void   setPassword(String p)  { this.password = p; }
}