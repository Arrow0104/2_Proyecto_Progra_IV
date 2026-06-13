package cr.ac.una.job.dtos.caracteristica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCaracteristicaRequest {

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;


    private Long padreId;

    public CreateCaracteristicaRequest() {}

    public CreateCaracteristicaRequest(String nombre, Long padreId) {
        this.nombre  = nombre;
        this.padreId = padreId;
    }

    public String getNombre()           { return nombre; }
    public void   setNombre(String n)   { this.nombre = n; }
    public Long   getPadreId()          { return padreId; }
    public void   setPadreId(Long id)   { this.padreId = id; }
}