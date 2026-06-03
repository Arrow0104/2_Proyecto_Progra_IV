package cr.ac.una.job.dtos.caracteristica;

public class CaracteristicaResponse {
    private Long   idCaracteristica;
    private String nombre;
    private Long   padreId;

    public CaracteristicaResponse() {}

    public CaracteristicaResponse(Long idCaracteristica, String nombre, Long padreId) {
        this.idCaracteristica = idCaracteristica;
        this.nombre           = nombre;
        this.padreId          = padreId;
    }

    public Long   getIdCaracteristica()            { return idCaracteristica; }
    public void   setIdCaracteristica(Long id)     { this.idCaracteristica = id; }
    public String getNombre()                      { return nombre; }
    public void   setNombre(String nombre)         { this.nombre = nombre; }
    public Long   getPadreId()                     { return padreId; }
    public void   setPadreId(Long padreId)         { this.padreId = padreId; }
}