package cr.ac.una.job.exceptions;

public class OferenteNotFoundException extends RuntimeException {
    public OferenteNotFoundException(Long id) {
        super("Oferente no encontrado con id: " + id);
    }
}